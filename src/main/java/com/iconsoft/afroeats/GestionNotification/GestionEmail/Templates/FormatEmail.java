package com.iconsoft.afroeats.GestionNotification.GestionEmail.Templates;

public class FormatEmail {

public static final String inscriptionSuccessMail = "<!DOCTYPE html>\n" +
        "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
        "<head>\n" +
        "    <meta charset=\"utf-8\"> <!-- utf-8 works for most cases -->\n" +
        "    <meta name=\"viewport\" content=\"width=device-width\"> <!-- Forcing initial-scale shouldn't be necessary -->\n" +
        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <!-- Use the latest (edge) version of IE rendering engine -->\n" +
        "    <meta name=\"x-apple-disable-message-reformatting\">  <!-- Disable auto-scale in iOS 10 Mail entirely -->\n" +
        "    <title></title> <!-- The title tag shows in email notifications, like Android 4.4. -->\n" +
        "\n" +
        "\n" +
        "    <link href=\"https://fonts.googleapis.com/css?family=Playfair+Display:400,400i,700,700i\" rel=\"stylesheet\">\n" +
        "\n" +
        "    <!-- CSS Reset : BEGIN -->\n" +
        "<style>\n" +
        "\n" +
        "html,\n" +
        "body {\n" +
        "    margin: 0 auto !important;\n" +
        "    padding: 0 !important;\n" +
        "    height: 100% !important;\n" +
        "    width: 100% !important;\n" +
        "    background: #f1f1f1;\n" +
        "}\n" +
        "\n" +
        "/* What it does: Stops email clients resizing small text. */\n" +
        "* {\n" +
        "    -ms-text-size-adjust: 100%;\n" +
        "    -webkit-text-size-adjust: 100%;\n" +
        "}\n" +
        "\n" +
        "/* What it does: Centers email on Android 4.4 */\n" +
        "div[style*=\"margin: 16px 0\"] {\n" +
        "    margin: 0 !important;\n" +
        "}\n" +
        "\n" +
        "/* What it does: Stops Outlook from adding extra spacing to tables. */\n" +
        "table,\n" +
        "td {\n" +
        "    mso-table-lspace: 0pt !important;\n" +
        "    mso-table-rspace: 0pt !important;\n" +
        "}\n" +
        "\n" +
        "/* What it does: Fixes webkit padding issue. */\n" +
        "table {\n" +
        "    border-spacing: 0 !important;\n" +
        "    border-collapse: collapse !important;\n" +
        "    table-layout: fixed !important;\n" +
        "    margin: 0 auto !important;\n" +
        "}\n" +
        "\n" +
        "/* What it does: Uses a better rendering method when resizing images in IE. */\n" +
        "img {\n" +
        "    -ms-interpolation-mode:bicubic;\n" +
        "}\n" +
        "\n" +
        "/* What it does: Prevents Windows 10 Mail from underlining links despite inline CSS. Styles for underlined links should be inline. */\n" +
        "a {\n" +
        "    text-decoration: none;\n" +
        "}\n" +
        "\n" +
        "/* What it does: A work-around for email clients meddling in triggered links. */\n" +
        "*[x-apple-data-detectors],  /* iOS */\n" +
        ".unstyle-auto-detected-links *,\n" +
        ".aBn {\n" +
        "    border-bottom: 0 !important;\n" +
        "    cursor: default !important;\n" +
        "    color: inherit !important;\n" +
        "    text-decoration: none !important;\n" +
        "    font-size: inherit !important;\n" +
        "    font-family: inherit !important;\n" +
        "    font-weight: inherit !important;\n" +
        "    line-height: inherit !important;\n" +
        "}\n" +
        "\n" +
        "/* What it does: Prevents Gmail from displaying a download button on large, non-linked images. */\n" +
        ".a6S {\n" +
        "    display: none !important;\n" +
        "    opacity: 0.01 !important;\n" +
        "}\n" +
        "\n" +
        "/* What it does: Prevents Gmail from changing the text color in conversation threads. */\n" +
        ".im {\n" +
        "    color: inherit !important;\n" +
        "}\n" +
        "\n" +
        "/* If the above doesn't work, add a .g-img class to any image in question. */\n" +
        "img.g-img + div {\n" +
        "    display: none !important;\n" +
        "}\n" +
        "\n" +
        "/* What it does: Removes right gutter in Gmail iOS app: https://github.com/TedGoas/Cerberus/issues/89  */\n" +
        "/* Create one of these media queries for each additional viewport size you'd like to fix */\n" +
        "\n" +
        "/* iPhone 4, 4S, 5, 5S, 5C, and 5SE */\n" +
        "@media only screen and (min-device-width: 320px) and (max-device-width: 374px) {\n" +
        "    u ~ div .email-container {\n" +
        "        min-width: 320px !important;\n" +
        "    }\n" +
        "}\n" +
        "/* iPhone 6, 6S, 7, 8, and X */\n" +
        "@media only screen and (min-device-width: 375px) and (max-device-width: 413px) {\n" +
        "    u ~ div .email-container {\n" +
        "        min-width: 375px !important;\n" +
        "    }\n" +
        "}\n" +
        "/* iPhone 6+, 7+, and 8+ */\n" +
        "@media only screen and (min-device-width: 414px) {\n" +
        "    u ~ div .email-container {\n" +
        "        min-width: 414px !important;\n" +
        "    }\n" +
        "}\n" +
        "\n" +
        "</style>\n" +
        "\n" +
        "    <!-- CSS Reset : END -->\n" +
        "\n" +
        "    <!-- Progressive Enhancements : BEGIN -->\n" +
        "<style>\n" +
        "\n" +
        ".primary{\n" +
        "\tbackground: #f3a333;\n" +
        "}\n" +
        "\n" +
        ".bg_white{\n" +
        "\tbackground: #ffffff;\n" +
        "}\n" +
        ".bg_light{\n" +
        "\tbackground: #fafafa;\n" +
        "}\n" +
        ".bg_black{\n" +
        "\tbackground: #000000;\n" +
        "}\n" +
        ".bg_dark{\n" +
        "\tbackground: rgba(0,0,0,.8);\n" +
        "}\n" +
        ".email-section{\n" +
        "\tpadding:2.5em;\n" +
        "}\n" +
        "\n" +
        "/*BUTTON*/\n" +
        ".btn{\n" +
        "\tpadding: 10px 15px;\n" +
        "}\n" +
        ".btn.btn-primary{\n" +
        "\tborder-radius: 30px;\n" +
        "\tbackground: #f3a333;\n" +
        "\tcolor: #ffffff;\n" +
        "}\n" +
        "\n" +
        "\n" +
        "\n" +
        "h1,h2,h3,h4,h5,h6{\n" +
        "\tfont-family: 'Playfair Display', serif;\n" +
        "\tcolor: #000000;\n" +
        "\tmargin-top: 0;\n" +
        "}\n" +
        "\n" +
        "body{\n" +
        "\tfont-family: 'Montserrat', sans-serif;\n" +
        "\tfont-weight: 400;\n" +
        "\tfont-size: 15px;\n" +
        "\tline-height: 1.8;\n" +
        "\tcolor: rgba(0,0,0,.4);\n" +
        "}\n" +
        "\n" +
        "a{\n" +
        "\tcolor: #f3a333;\n" +
        "}\n" +
        "\n" +
        "table{\n" +
        "}\n" +
        "/*LOGO*/\n" +
        "\n" +
        ".logo h1{\n" +
        "\tmargin: 0;\n" +
        "}\n" +
        ".logo h1 a{\n" +
        "\tcolor: #000;\n" +
        "\tfont-size: 20px;\n" +
        "\tfont-weight: 700;\n" +
        "\ttext-transform: uppercase;\n" +
        "\tfont-family: 'Montserrat', sans-serif;\n" +
        "}\n" +
        "\n" +
        "/*HERO*/\n" +
        ".hero{\n" +
        "\tposition: relative;\n" +
        "}\n" +
        ".hero img{\n" +
        "\n" +
        "}\n" +
        ".hero .text{\n" +
        "\tcolor: rgba(255,255,255,.8);\n" +
        "}\n" +
        ".hero .text h2{\n" +
        "\tcolor: #ffffff;\n" +
        "\tfont-size: 30px;\n" +
        "\tmargin-bottom: 0;\n" +
        "}\n" +
        "\n" +
        "\n" +
        "/*HEADING SECTION*/\n" +
        ".heading-section{\n" +
        "}\n" +
        ".heading-section h2{\n" +
        "\tcolor: #000000;\n" +
        "\tfont-size: 28px;\n" +
        "\tmargin-top: 0;\n" +
        "\tline-height: 1.4;\n" +
        "}\n" +
        ".heading-section .subheading{\n" +
        "\tmargin-bottom: 20px !important;\n" +
        "\tdisplay: inline-block;\n" +
        "\tfont-size: 13px;\n" +
        "\ttext-transform: uppercase;\n" +
        "\tletter-spacing: 2px;\n" +
        "\tcolor: rgba(0,0,0,.4);\n" +
        "\tposition: relative;\n" +
        "}\n" +
        ".heading-section .subheading::after{\n" +
        "\tposition: absolute;\n" +
        "\tleft: 0;\n" +
        "\tright: 0;\n" +
        "\tbottom: -10px;\n" +
        "\tcontent: '';\n" +
        "\twidth: 100%;\n" +
        "\theight: 2px;\n" +
        "\tbackground: #f3a333;\n" +
        "\tmargin: 0 auto;\n" +
        "}\n" +
        "\n" +
        ".heading-section-white{\n" +
        "\tcolor: rgba(255,255,255,.8);\n" +
        "}\n" +
        ".heading-section-white h2{\n" +
        "\tfont-size: 28px;\n" +
        "\tfont-family: \n" +
        "\tline-height: 1;\n" +
        "\tpadding-bottom: 0;\n" +
        "}\n" +
        ".heading-section-white h2{\n" +
        "\tcolor: #ffffff;\n" +
        "}\n" +
        ".heading-section-white .subheading{\n" +
        "\tmargin-bottom: 0;\n" +
        "\tdisplay: inline-block;\n" +
        "\tfont-size: 13px;\n" +
        "\ttext-transform: uppercase;\n" +
        "\tletter-spacing: 2px;\n" +
        "\tcolor: rgba(255,255,255,.4);\n" +
        "}\n" +
        "\n" +
        "\n" +
        ".icon{\n" +
        "\ttext-align: center;\n" +
        "}\n" +
        ".icon img{\n" +
        "}\n" +
        "\n" +
        "\n" +
        "/*SERVICES*/\n" +
        ".text-services{\n" +
        "\tpadding: 10px 10px 0; \n" +
        "\ttext-align: center;\n" +
        "}\n" +
        ".text-services h3{\n" +
        "\tfont-size: 20px;\n" +
        "}\n" +
        "\n" +
        "/*BLOG*/\n" +
        ".text-services .meta{\n" +
        "\ttext-transform: uppercase;\n" +
        "\tfont-size: 14px;\n" +
        "}\n" +
        "\n" +
        "/*TESTIMONY*/\n" +
        ".text-testimony .name{\n" +
        "\tmargin: 0;\n" +
        "}\n" +
        ".text-testimony .position{\n" +
        "\tcolor: rgba(0,0,0,.3);\n" +
        "\n" +
        "}\n" +
        "\n" +
        "\n" +
        "/*VIDEO*/\n" +
        ".img{\n" +
        "\twidth: 100%;\n" +
        "\theight: auto;\n" +
        "\tposition: relative;\n" +
        "}\n" +
        ".img .icon{\n" +
        "\tposition: absolute;\n" +
        "\ttop: 50%;\n" +
        "\tleft: 0;\n" +
        "\tright: 0;\n" +
        "\tbottom: 0;\n" +
        "\tmargin-top: -25px;\n" +
        "}\n" +
        ".img .icon a{\n" +
        "\tdisplay: block;\n" +
        "\twidth: 60px;\n" +
        "\tposition: absolute;\n" +
        "\ttop: 0;\n" +
        "\tleft: 50%;\n" +
        "\tmargin-left: -25px;\n" +
        "}\n" +
        "\n" +
        "\n" +
        "\n" +
        "/*COUNTER*/\n" +
        ".counter-text{\n" +
        "\ttext-align: center;\n" +
        "}\n" +
        ".counter-text .num{\n" +
        "\tdisplay: block;\n" +
        "\tcolor: #ffffff;\n" +
        "\tfont-size: 34px;\n" +
        "\tfont-weight: 700;\n" +
        "}\n" +
        ".counter-text .name{\n" +
        "\tdisplay: block;\n" +
        "\tcolor: rgba(255,255,255,.9);\n" +
        "\tfont-size: 13px;\n" +
        "}\n" +
        "\n" +
        "\n" +
        "/*FOOTER*/\n" +
        "\n" +
        ".footer{\n" +
        "\tcolor: rgba(255,255,255,.5);\n" +
        "\n" +
        "}\n" +
        ".footer .heading{\n" +
        "\tcolor: #ffffff;\n" +
        "\tfont-size: 20px;\n" +
        "}\n" +
        ".footer ul{\n" +
        "\tmargin: 0;\n" +
        "\tpadding: 0;\n" +
        "}\n" +
        ".footer ul li{\n" +
        "\tlist-style: none;\n" +
        "\tmargin-bottom: 10px;\n" +
        "}\n" +
        ".footer ul li a{\n" +
        "\tcolor: rgba(255,255,255,1);\n" +
        "}\n" +
        "\n" +
        "\n" +
        "@media screen and (max-width: 500px) {\n" +
        "\n" +
        "\t.icon{\n" +
        "\t\ttext-align: left;\n" +
        "\t}\n" +
        "\n" +
        "\t.text-services{\n" +
        "\t\tpadding-left: 0;\n" +
        "\t\tpadding-right: 20px;\n" +
        "\t\ttext-align: left;\n" +
        "\t}\n" +
        "\n" +
        "}\n" +
        "\n" +
        "</style>\n" +
        "\n" +
        "\n" +
        "</head>\n" +
        "\n" +
        "<body width=\"100%\" style=\"margin: 0; padding: 0 !important; mso-line-height-rule: exactly; background-color: #222222;\">\n" +
        "\t<center style=\"width: 100%; background-color: #f1f1f1;\">\n" +
        "    <div style=\"display: none; font-size: 1px;max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\">\n" +
        "      &zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;\n" +
        "    </div>\n" +
        "    <div style=\"max-width: 600px; margin: 0 auto;\" class=\"email-container\">\n" +
        "    \t<!-- BEGIN BODY -->\n" +
        "      <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"margin: auto;\">\n" +
        "      \t<tr>\n" +
        "          <td class=\"bg_white logo\" style=\"padding: 1em 2.5em; text-align: center\">\n" +
        "\t\t\t<img src=\"https://res.cloudinary.com/icon-soft/image/upload/v1642854940/email/logo_kve6wj.png\" alt=\"\" style=\"width:200px; height:50px;\"/>\n" +
        "          </td>\n" +
        "\t      </tr><!-- end tr -->\n" +
        "\t\t\t\t<tr>\n" +
        "          <td valign=\"middle\" class=\"hero\" style=\"background-image: url(https://res.cloudinary.com/icon-soft/image/upload/v1642854940/email/bg_1_bfbdcb.jpg); background-size: cover; height: 400px;\">\n" +
        "            <table>\n" +
        "            \t<tr>\n" +
        "            \t\t<td>\n" +
        "            \t\t\t<div class=\"text\" style=\"padding: 0 3em; text-align: center;\">\n" +
        "            \t\t\t\t<h2>Au service du goût</h2>\n" +
        "\t\t\t\t\t\t\t\t<h2>&amp; </h2>\n" +
        "\t\t\t\t\t\t\t<h2>Des délicieux repas africains</h2>\n" +
        "            \t\t\t\t<p><a href=\"#\" class=\"btn btn-primary\">Se rendre sur le site!</a></p>\n" +
        "            \t\t\t</div>\n" +
        "            \t\t</td>\n" +
        "            \t</tr>\n" +
        "            </table>\n" +
        "          </td>\n" +
        "\t      </tr><!-- end tr -->\n" +
        "\t      <tr>\n" +
        "\t\t      <td class=\"bg_white\">\n" +
        "\t\t        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
        "\t\t          \t<tr>\n" +
        "\t\t\t\t  \t\t<td class=\"bg_white email-section\">\n" +
        "\t\t\t\t\t<div class=\"heading-section\" style=\"text-align: center; padding: 0 30px;\">\n" +
        "\t\t            \t<span class=\"subheading\">Inscription</span>\n" +
        "\t\t              \t<h2></h2>\n" +
        "\t\t\t\t\t\t<p>Nous vous remercions pour votre inscription. N'hésitez pas à passez vos commandes.</p>\n" +
        "\t\t\t\t\t\t<p>Rendez-vous sur notre page <a href=\"#\">AfroEats</a>. Nos services clients sont disponible 7J/7.</p>\n" +
        "\t\t            </div>\n" +
        "\t\t\t\t\t</td>\n" +
        "\t\t\t\t  </tr>\n" +
        "\t\t\t\t  <tr>\n" +
        "\t\t            <td class=\"bg_dark email-section\" style=\"text-align:center;\">\n" +
        "\t\t            \t<div class=\"heading-section heading-section-white\">\n" +
        "\t\t            \t\t<span class=\"subheading\">Bienvenue</span>\n" +
        "\t\t              \t<h2>Bienvenue sur AfroEats</h2>\n" +
        "\t\t              \t<p>Une plateforme de vulgarisation de la culture africaine, avec de délicieux plats de toutes les horizons. Passez une commande et faites vous livrer.</p>\n" +
        "\t\t            \t</div>\n" +
        "\t\t            </td>\n" +
        "\t\t          </tr><!-- end: tr -->\n" +
        "\t\t          <tr>\t\t\t\t\n" +
        "\t\t            <td class=\"bg_white email-section\">\n" +
        "\t\t            \t<div class=\"heading-section\" style=\"text-align: center; padding: 0 30px;\">\n" +
        "\t\t            \t\t<span class=\"subheading\">Services</span>\n" +
        "\t\t              \t<h2>Nos Services</h2>\n" +
        "\t\t            \t</div>\n" +
        "\t\t            \t<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
        "\t\t            \t\t<tr>\n" +
        "                      <td valign=\"top\" width=\"50%\" style=\"padding-top: 20px;\">\n" +
        "                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
        "                          <tr>\n" +
        "                            <td class=\"icon\">\n" +
        "                              <img src=\"https://res.cloudinary.com/icon-soft/image/upload/v1642854939/email/003-recipe-book_cbjer5.png\" alt=\"\" style=\"width: 60px; max-width: 600px; height: auto; margin: auto; display: block;\">\n" +
        "                            </td>\n" +
        "                          </tr>\n" +
        "                          <tr>\n" +
        "                            <td class=\"text-services\">\n" +
        "                            \t<h3>Plats africains</h3>\n" +
        "                              <p>Commandez et faites vous livrer, des savoureux plats.</p>\n" +
        "                            </td>\n" +
        "                          </tr>\n" +
        "                        </table>\n" +
        "                      </td>\n" +
        "\t\t\t\t\t                        <td valign=\"top\" width=\"50%\" style=\"padding-top: 20px;\">\n" +
        "                        <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
        "                          <tr>\n" +
        "                            <td class=\"icon\">\n" +
        "                              <img src=\"https://res.cloudinary.com/icon-soft/image/upload/v1642854939/email/001-diet_g6lk9v.png\" alt=\"\" style=\"width: 60px; max-width: 600px; height: auto; margin: auto; display: block;\">\n" +
        "                            </td>\n" +
        "                          </tr>\n" +
        "                          <tr>\n" +
        "                            <td class=\"text-services\">\n" +
        "                            \t<h3>Vivre frais</h3>\n" +
        "                             \t<p>Plus besoin de vous déplacer. Vous êtes livré devant votre porte.</p>\n" +
        "                            </td>\n" +
        "                          </tr>\n" +
        "                        </table>\n" +
        "                      </td>\n" +
        "                    </tr>\n" +
        "\t\t            \t</table>\n" +
        "\t\t            </td>\n" +
        "\t\t          </tr><!-- end: tr -->\n" +
        "      <!-- 1 Column Text + Button : END -->\n" +
        "      </table>\n" +
        "      <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\" style=\"margin: auto;\">\n" +
        "        <tr>\n" +
        "        \t<td valign=\"middle\" class=\"bg_black footer email-section\">\n" +
        "        \t\t<table>\n" +
        "            \t<tr>\n" +
        "                <td valign=\"top\" width=\"33.333%\">\n" +
        "                  <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
        "                    <tr>\n" +
        "                      <td style=\"text-align: left; padding-right: 10px;\">\n" +
        "                      \t<p>&copy; 2021 IconSoft.</p>\n" +
        "                      </td>\n" +
        "                    </tr>\n" +
        "                  </table>\n" +
        "                </td>\n" +
        "\t\t\t\t<td>\n" +
        "                  <table role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n" +
        "                    <tr>\n" +
        "                      <td style=\"text-align: right; padding-left: 5px; padding-right: 5px;\">\n" +
        "                      \t<p><a href=\"#\" style=\"color: rgba(255,255,255,.4);\">Contact : (003)3758732693</a></p>\n" +
        "                      </td>\n" +
        "                    </tr>\n" +
        "                  </table>\n" +
        "                </td>\n" +
        "              </tr>\n" +
        "            </table>\n" +
        "        \t</td>\n" +
        "        </tr>\n" +
        "      </table>\n" +
        "\n" +
        "    </div>\n" +
        "  </center>\n" +
        "</body>\n" +
        "</html>";

}
