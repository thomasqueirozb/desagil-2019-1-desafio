package br.pro.hashi.ensino.desagil.desafio;

import br.pro.hashi.ensino.desagil.desafio.model.CpuPlayer;
import br.pro.hashi.ensino.desagil.desafio.model.Element;
import br.pro.hashi.ensino.desagil.desafio.model.HumanPlayer;
import br.pro.hashi.ensino.desagil.desafio.model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener, ActionListener {
    private final Model model;
    private final View view;


    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }


    @Override
    public void keyTyped(KeyEvent event) {
        // Neste programa, não é necessário definir o que o controlador
        // faz quando um caractere é digitado, mas implementar KeyListener
        // obriga esse método a existir. Então deixamos vazio.
    }


    @Override
    public void keyPressed(KeyEvent event) {
        if (model.getWinner()==null) {
            HumanPlayer humanPlayer = model.getHumanPlayer();

            // Para agir de acordo com a tecla que foi pressionada, comparamos o key code do evento com as
            // constantes disponíveis na classe KeyEvent. Uma lista dessas constantes pode ser vista em
            // https://docs.oracle.com/en/java/javase/11/docs/api/java.desktop/java/awt/event/KeyEvent.html.
            switch (event.getKeyCode()) {
                case KeyEvent.VK_UP:
                    humanPlayer.moveUp();
                    break;
                case KeyEvent.VK_RIGHT:
                    humanPlayer.moveRight();
                    break;
                case KeyEvent.VK_DOWN:
                    humanPlayer.moveDown();
                    break;
                case KeyEvent.VK_LEFT:
                    humanPlayer.moveLeft();
                    break;
            }

            view.repaint();
        }
    }


    @Override
    public void keyReleased(KeyEvent event) {
        // Neste programa, não é necessário definir o que o controlador
        // faz quando uma tecla é solta, mas implementar KeyListener
        // obriga esse método a existir. Então deixamos vazio.
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (model.getWinner()==null){
            CpuPlayer cpuPlayer = model.getCpuPlayer();
            cpuPlayer.move();

            Element target = model.getTarget();
            HumanPlayer humanPlayer = model.getHumanPlayer();

            if (target.getCol()==cpuPlayer.getCol() & target.getRow()==cpuPlayer.getRow()){
                model.setWinner(cpuPlayer);
            }
            else if ((target.getCol()==humanPlayer.getCol()) & (target.getRow()==humanPlayer.getRow())){
                model.setWinner(humanPlayer);
            }

        }
        view.repaint();
    }
}
