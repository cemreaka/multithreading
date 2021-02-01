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
public class Node extends Thread {

    private Node parent;
    private int id;
    private ArrayList<Node> children = new ArrayList<>();
    private int height;
    private ArrayList<Integer> ack_messages = new ArrayList<>();

    public Node(Node parent, int id) {
        this.parent = parent;
        this.id = id;
    }

    @Override
    public void run() {
        sendMessage();
    }

    public Node getParent() {
        return parent;
    }

    public void sendMessage() {
        if (parent != null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        if (!children.isEmpty()) {
            System.out.printf("%s works and sends height message to children\n", Thread.currentThread().getName());
            sendHeightMessage();
        } else {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.printf("%s works and sends height_ack message\n", Thread.currentThread().getName());
            sendAckMessage(height);
        }
    }

    public void addChild(Node n) {
        children.add(n);
    }

    public void sendAckMessage(int h) {
        ack_messages.add(h);
        checkAckMessage(h);
    }

    public void sendHeightMessage() {
        for (int i = 0; i < children.size(); i++) {
            System.out.println("From Node " + id + " to Node " + children.get(i).getNodeId() + " Height Message = " + (height + 1));
            children.get(i).heightMessage(height + 1);
        }
    }

    public int getNodeId() {
        return id;
    }

    public void heightMessage(int heightMessage) {
        this.height = heightMessage;
    }

    public void checkAckMessage(int h) {
        int max = h;
        if (parent != null) {
            for (int i = 0; i < ack_messages.size(); i++) {
                max = Math.max(max, ack_messages.get(i));
            }
            System.out.println("From Node " + getNodeId() + " to Parent Node " + parent.getNodeId() + " height_ack Message with number " + max);
            parent.sendAckMessage(max);

        } else {
            for (int i = 0; i < ack_messages.size(); i++) {
                max = Math.max(max, ack_messages.get(i));
            }
            height = max;
            if (ack_messages.size() != 1) {
                System.out.println("Height is " + height);
            }
        }

    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public int getHeight() {
        return height;
    }

}
