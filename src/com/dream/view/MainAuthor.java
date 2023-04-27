package com.dream.view;

import com.dream.controller.AuthorController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;


public class MainAuthor {

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter author ID: ");
        String author_id = scanner.nextLine();
        System.out.print("Enter language (e.g. en, es, fr): ");
        String hl = scanner.nextLine();

        AuthorController authorController = new AuthorController(author_id, hl);
        authorController.insertAuthor();
    }
}