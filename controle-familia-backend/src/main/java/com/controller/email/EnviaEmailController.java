package com.controller.email;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class EnviaEmailController {

    @Inject
    Mailer mailer;

    public void enviaEmail(String dsLink, String dsEmailDestinatario, String dsNomeUsuario) {

        String bodyEmail = layoutEmail()
                .replace("#dsLink", dsLink)
                .replace("#nameUsuario", dsNomeUsuario);

        mailer.send(Mail.withHtml(dsEmailDestinatario, "Redefinir Senha Home Money Manager", bodyEmail));

    }

    private String layoutEmail() {

        File fileHtml = new File("src/main/resources/templates/template-recuperar-senha.html");

        try {
            FileInputStream fileInputStream = new FileInputStream(fileHtml);
            String fileStrig = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
            fileInputStream.close();
            return fileStrig;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
