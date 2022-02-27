package com.iconsoft.afroeats.GestionPanier.Services;

import com.iconsoft.afroeats.GestionErreurs.ErrorMessages;
import com.iconsoft.afroeats.GestionPanier.DTO.PanieritemDTO;
import com.iconsoft.afroeats.GestionPanier.Metier.PanierMetier;
import com.iconsoft.afroeats.GestionPanier.Models.Panier;
import com.iconsoft.afroeats.GestionPanier.Models.Panieritem;
import com.iconsoft.afroeats.GestionPanier.Repository.PanierRepository;
import com.iconsoft.afroeats.GestionPanier.Repository.PanieritemRepository;
import com.iconsoft.afroeats.GestionProduits.Models.Article;
import com.iconsoft.afroeats.GestionProduits.Repository.ArticleRepository;
import com.iconsoft.afroeats.GestionProduits.Repository.PlatRepository;
import com.iconsoft.afroeats.GestionProduits.Repository.ProduitRepository;
import com.iconsoft.afroeats.GestionUtilisateurs.Repository.ClientAfroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PanierServices implements PanierMetier {
    @Autowired
    PanierRepository panierRepository;
    @Autowired
    PanieritemRepository panieritemRepository;
    @Autowired
    ClientAfroRepository clientAfroRepository;
    @Autowired
    ArticleRepository  articleRepository;
    @Autowired
    PlatRepository platRepository;
    @Autowired
    ProduitRepository produitRepository;

    @Override
    public Panier savePanier(Panier panier) {
        return panierRepository.save(panier);
    }

    @Override
    public Panier findPanierById(Long idpanier) {
        return panierRepository.findByIdpanier(idpanier);
    }

    @Override
    public Panier findPanierByReference(String reference) {
        return panierRepository.findByReference(reference);
    }

    @Override
    public Panier viderPanier(String reference) {
        Panier panier = panierRepository.findByReference(reference);
        if(panier!=null){
            if (panier.getPanieritems() != null) {
                panieritemRepository.deleteAll(panier.getPanieritems());
                panier.setMontanttotal(0);
                return panier;
            }else
                throw new ErrorMessages("Aucun éléments trouvé dans le panier : " + reference, HttpStatus.NOT_FOUND);
        } else
            throw new ErrorMessages("Le panier : " + reference + " n'existe pas.", HttpStatus.NOT_FOUND);
    }

    @Override
    public Panieritem savePanieritem(Panieritem panieritem) {
        return panieritemRepository.save(panieritem);
    }

    @Override
    public List<Panieritem> saveAllPanierItems(List<Panieritem> panieritems) {
        panieritems.forEach(panieritem -> panieritem.setMontant(panieritem.getQuantite() * panieritem.getPrixunitaire()));
        return panieritemRepository.saveAll(panieritems);
    }

    @Override
    public boolean panierItemAction(PanieritemDTO panieritemDTO) {
        Article article = null;
        if(panieritemDTO.getTypeproduit().equalsIgnoreCase("plat")){
            article = platRepository.findByIdarticle(panieritemDTO.getIdarticle());
        } else if(panieritemDTO.getTypeproduit().equalsIgnoreCase("produit")){
            article = produitRepository.findByIdarticle(panieritemDTO.getIdarticle());
        }

        if(article==null) throw new ErrorMessages("L'article choisi n'existe pas.", HttpStatus.NOT_FOUND);
        Panier panier = panierRepository.findByReference(panieritemDTO.getReference());
        if(panier==null) throw new ErrorMessages("Le panier sélectionné n'existe pas.", HttpStatus.NOT_FOUND);

        if(panieritemDTO.getQuantite()<=0) throw new ErrorMessages("La quantité à définir de " + article.getNomarticle() + " est incohérente. > " + panieritemDTO.getQuantite() , HttpStatus.BAD_REQUEST);

        if(panieritemDTO.getTypeaction().equalsIgnoreCase("add")){
                Panieritem panieritem = panieritemRepository.getPanieritemByReferenceAndArticle(panier.getReference(), article.getNomarticle());
                if(panieritem!=null){
                   if (!panieritem.getJour().equals(panieritemDTO.getJour())) throw new ErrorMessages("Vous ne pouvez pas mettre dans le panier des articles de deux jour different",HttpStatus.CONFLICT);
                    panieritem.setQuantite(panieritem.getQuantite()+1);
                    panieritem.setMontant(panieritem.getQuantite() * article.getPrixarticle());
                    panieritemRepository.save(panieritem);
                } else {
                    Panieritem pi = new Panieritem();
                    pi.setPanier(panier);
                    pi.setDescription(article.getNomarticle());
                    pi.setMontant(panieritemDTO.getQuantite() * article.getPrixarticle());
                    pi.setPrixunitaire(article.getPrixarticle());
                    pi.setQuantite(panieritemDTO.getQuantite());
                    pi.setNomarticle(article.getNomarticle());
                    pi.setJour(panieritemDTO.getJour());
                    pi.setArticle(article);
                    pi = panieritemRepository.save(pi);
                    if(pi.getIdpanieritem()==null) throw new ErrorMessages("L'élément du panier n'a pas été enregistré", HttpStatus.CONFLICT);
                    panier.getPanieritems().add(pi);
                }
        } else if (panieritemDTO.getTypeaction().equalsIgnoreCase("remove")){
            Panieritem panieritem = panieritemRepository.getPanieritemByReferenceAndArticle(panier.getReference(), article.getNomarticle());
            if(panieritem!=null){
                panieritem.setQuantite(panieritem.getQuantite()-1);
                panieritem.setMontant(panieritemDTO.getQuantite() * article.getPrixarticle());
            }

        }  else if (panieritemDTO.getTypeaction().equalsIgnoreCase("delete")){
            Panieritem panieritem = panieritemRepository.getPanieritemByReferenceAndArticle(panier.getReference(), article.getNomarticle());
            if(panieritem!=null){
                panier.getPanieritems().remove(panieritem);
                panieritemRepository.delete(panieritem);
            }
        }

        panier.setMontanttotal(0);
        panier.getPanieritems().forEach(ci-> panier.setMontanttotal(panier.getMontanttotal() + ci.getMontant()));
        panierRepository.save(panier);

        return true;
    }

    @Override
    public boolean deletePanierItem(Panieritem panieritem) {
        Panieritem pi = panieritemRepository.findByIdpanieritem(panieritem.getIdpanieritem());
        if(pi == null){
            throw new ErrorMessages("L'élément à supprimer n'existe pas.", HttpStatus.NOT_FOUND);
        } else {
            panieritemRepository.delete(panieritem);
        }
        Panieritem pait = panieritemRepository.findByIdpanieritem(panieritem.getIdpanieritem());
        return pait == null;
    }

    @Override
    public List<Panieritem> getAllCartItemsByReference(String reference) {
        return panieritemRepository.getAllPanierItemByReferencePanier(reference);
    }
}
