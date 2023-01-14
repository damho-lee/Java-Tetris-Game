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

        statusbar = new JLabel(" 0"); //�젏�닔
        add(statusbar, BorderLayout.SOUTH); //�궓履쎌뿉 諛곗튂

        var board = new Board(this);
        add(board);
        board.start();

        setTitle("Tetris");                //title紐�
        setSize(400, 800);         //�궗�씠利� 議곗젅
        setDefaultCloseOperation(EXIT_ON_CLOSE); //x�늻瑜대㈃ �옉�룞 �걹
        setLocationRelativeTo(null);          //�솕硫댁쓽 媛��슫�뜲 �뀒�듃由ъ뒪 寃뚯엫 異쒕젰
    }

    JLabel getStatusBar() {

        return statusbar;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {//�씠踰ㅽ듃 �뵒�뒪�뙣移� �벐�젅�뱶�뿉 �쓽�빐 Runnalbe.run()�쓣 �떎�뻾�븳�떎. 
                                      //�씠 硫붿냼�뱶瑜� �샇異쒗븯硫� �씠踰ㅽ듃 �걧�뿉 runnable.run()�쓽 �궡�슜�쓣 �꽔怨� 諛붾줈 由ы꽩�븳�떎.

            var game = new Tetris();
            game.setVisible(true); 
        });
    }    
}
