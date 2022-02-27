package com.iconsoft.afroeats.Cloudinary.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.iconsoft.afroeats.Cloudinary.Models.Cloudinarie;
import com.iconsoft.afroeats.Cloudinary.Repository.CloudinaryRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Transactional
@Service
public class ServiceCloudinary {
    @Autowired
    CloudinaryRepository cloudinaryRepository;

    private Cloudinary cloudinary;
    private Cloudinarie cloudinarie;

    public String saveObjectOnCloudinary(String imagestring,
                                         String categorie,
                                         String nomelement,
                                         String objectName) {
        cloudinarie = new Cloudinarie();
        cloudinary = cloudinaryConfig();
        try {

            // Get extension on string
            String ext = imagestring.substring(imagestring.indexOf("/") + 1, imagestring.indexOf(";") - 1);
            System.out.println(">>>>>>>>" + imagestring);
            System.out.println(">>>>>>>>" + ext);

            // Get base image string
            String image = imagestring.substring(imagestring.indexOf(",") + 1);
            System.out.println(">>>>>>>>" + imagestring);
            System.out.println(">>>>>>>>" + image);

            //Suppression du fichier s'il existe deja
            this.deleteFile("temp." + ext);

            // decode the string and write to file
            byte[] decodedBytes = Base64
                    .getDecoder()
                    .decode(image);
            File file = new File(System.getProperty("user.dir") + "/temp." + ext);
            FileUtils.writeByteArrayToFile(file, decodedBytes);


            //Get Source of image
            String src = "";

            if (categorie.equalsIgnoreCase("users")) {
                src = categorie + "/" + nomelement + "/" + objectName;
            } else if (categorie.equalsIgnoreCase("articles")) {
                src = categorie + "/" + nomelement + "/articles/" + objectName;
            } else if (categorie.equalsIgnoreCase("restaurant")) {
                src = categorie + "/" + nomelement + "/restaurant/" + objectName;
            } else if (categorie.equalsIgnoreCase("bannieres")) {
                src = categorie + "/" + objectName;
            }else if (categorie.equalsIgnoreCase("categorie")) {
                src = categorie + "/" + objectName;
            }

            //Upload de l'image
            Map result = cloudinary.uploader().upload(file, ObjectUtils.asMap("public_id", src));
            //Map result=cloudinary.uploader().upload(file,ObjectUtils.emptyMap());
            cloudinarie.setSignature((String) result.get("signature"));
            cloudinarie.setFormat((String) result.get("format"));
            cloudinarie.setSecure_url(((String) result.get("secure_url")));
            cloudinarie.setUrl(result.get("url").toString());
            cloudinarie.setPublic_id(result.get("public_id").toString());
            cloudinarie.setType(result.get("type").toString());
            cloudinarie.setOriginal_filname((String) result.get("original_filname"));
            cloudinarie = cloudinaryRepository.save(cloudinarie);
            boolean b = file.delete();
            if (!b)
                this.deleteFile("temp." + ext);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url=cloudinarie.getUrl();
        if (cloudinarie.getUrl() != null) {
    if (cloudinarie.getUrl().contains("http://")){
        url=cloudinarie.getUrl().replace("http","https");

    }
        }
        return url;
    }

    public String uploadImage(MultipartFile multipartFile,
                              String categorie,
                              String nomelement,
                              String objectName) {
        cloudinarie = new Cloudinarie();
        cloudinary = cloudinaryConfig();
        File file = convert(multipartFile);

//Get Source of image
        String src = "";

        if (categorie.equalsIgnoreCase("users")) {
            src = categorie + "/" + nomelement + "/" + objectName;
        } else if (categorie.equalsIgnoreCase("articles")) {
            src = categorie + "/" + nomelement + "/articles/" + objectName;
        } else if (categorie.equalsIgnoreCase("publicites")) {
            src = categorie + "/" + nomelement + "/publicites/" + objectName;
        } else if (categorie.equalsIgnoreCase("bannieres")) {
            src = categorie + "/" + objectName;
        }

        //Upload de l'image
        Map result = null;
        try {
            result = cloudinary.uploader().upload(file, ObjectUtils.asMap("public_id", src));
            //Map result=cloudinary.uploader().upload(file,ObjectUtils.emptyMap());
            cloudinarie.setSignature((String) result.get("signature"));
            cloudinarie.setFormat((String) result.get("format"));
            cloudinarie.setSecure_url(((String) result.get("secure_url")));
            cloudinarie.setUrl(result.get("url").toString());
            cloudinarie.setPublic_id(result.get("public_id").toString());
            cloudinarie.setType(result.get("type").toString());
            cloudinarie.setOriginal_filname(result.get("original_filname").toString());
            cloudinarie = cloudinaryRepository.save(cloudinarie);
            supprFile(file); //Suppression du fichier
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cloudinarie.getUrl();
    }

    public Cloudinary cloudinaryConfig() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", "icon-soft");
        config.put("api_key", "131434686148586");
        config.put("api_secret", "O6rMH7IXxTlEZcfD0eHGah5RU3o");
        cloudinary = new Cloudinary(config);
        System.out.println(config);
        return cloudinary;
    }

    void deleteFile(String imagename) {
        String current = System.getProperty("user.dir");
        try {
            File file = new File(current + "/" + imagename);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void supprFile(File file) {
        try {
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File convert(MultipartFile multipartFile) {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try {
            FileOutputStream fo = null;
            fo = new FileOutputStream(file);
            fo.write(multipartFile.getBytes());
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
