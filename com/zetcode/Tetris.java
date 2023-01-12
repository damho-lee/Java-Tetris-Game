package com.zetcode;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;

/*
Java Tetris game clone

Author: Jan Bodnar
Website: https://zetcode.com
 */
public class Tetris extends JFrame {

    private JLabel statusbar;

    public Tetris() {

        initUI();
    }

    private void initUI() {

        statusbar = new JLabel(" 0"); //점수
        add(statusbar, BorderLayout.SOUTH); //남쪽에 배치

        var board = new Board(this);
        add(board);
        board.start();

        setTitle("Tetris");                //title명
        setSize(400, 800);         //사이즈 조절
        setDefaultCloseOperation(EXIT_ON_CLOSE); //x누르면 작동 끝
        setLocationRelativeTo(null);          //화면의 가운데 테트리스 게임 출력
    }

    JLabel getStatusBar() {

        return statusbar;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {//이벤트 디스패칭 쓰레드에 의해 Runnalbe.run()을 실행한다. 
                                      //이 메소드를 호출하면 이벤트 큐에 runnable.run()의 내용을 넣고 바로 리턴한다.

            var game = new Tetris();
            game.setVisible(true); 
        });
    }
}
