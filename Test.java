/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpmultithreadedapp;

import java.util.ArrayList;

/**
 *
 * @author cemreaka
 */
public class Test {

    public static void main(String[] args) {
        Node n0 = new Node(null, 0);
        Node n1 = new Node(n0, 1);
        Node n2 = new Node(n1, 2);
        Node n3 = new Node(n1, 3);
        Node n4 = new Node(n3, 4);
        n0.addChild(n1);
        n1.addChild(n2);
        n1.addChild(n3);
        n3.addChild(n4);
        ArrayList<Node> allNodes = new ArrayList<>();
        allNodes.add(n0);
        allNodes.add(n1);
        allNodes.add(n2);
        allNodes.add(n3);
        allNodes.add(n4);

        for (Node n : allNodes) {
            n.start();
        }
    }

}


/*  
                                  root(n0)
                                     
                                     n1
                          n2                 n3                 my spanning tree
                                                   n4
 */
