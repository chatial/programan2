/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Perfecto
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HelloGridLayout extends JFrame implements ActionListener {
    private JButton buttonA;
    private JButton buttonB;
    private JButton buttonC;
    private JButton buttonD;
    private JButton buttonE;
    private JButton buttonF;
    private JButton buttonG;
    private JButton buttonH;
    private JButton buttonI;
   
    private JButton[] buttons;
    private boolean gameOver;
    
    public HelloGridLayout(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gameOver = false;
        
        buttonA = new JButton("");
        buttonB = new JButton("");
        buttonC = new JButton("");
        buttonD = new JButton("");
        buttonE = new JButton("");
        buttonF = new JButton("");
        buttonG= new JButton("");
        buttonH = new JButton("");
        buttonI = new JButton("");
        
        buttons = new JButton[9];
        buttons[0] = buttonA;
        buttons[1] = buttonB;
        buttons[2] = buttonC;
        buttons[3] = buttonD;
        buttons[4] = buttonE;
        buttons[5] = buttonF;
        buttons[6] = buttonG;
        buttons[7] = buttonH;
        buttons[8] = buttonI;
        
        buttonA.addActionListener(this);
        buttonB.addActionListener(this);
        buttonC.addActionListener(this);
        buttonD.addActionListener(this);
        buttonE.addActionListener(this);
        buttonF.addActionListener(this);
        buttonG.addActionListener(this);
        buttonH.addActionListener(this);
        buttonI.addActionListener(this);
        
        this.setLayout(new GridLayout(3, 3));
        
        this.add(buttonA);
        this.add(buttonB);
        this.add(buttonC);
        this.add(buttonD);
        this.add(buttonE);
        this.add(buttonF);
        this.add(buttonG);
        this.add(buttonH);
        this.add(buttonI);
        
        this.setSize(300, 300);
        
        public void actionPerformed(ActionEvent e){
            if (!gameOver)
            {
            JButton button = (JButton) e.getSource();
            if (button.getText().isEmpty())
            {
                button.setText("0");  
                checkWinner();
                if (!gameOver)
                {
                    for (int i = 0; i < buttons.length; i++)
                    {
                        if (buttons[i].getText().isEmpty())
                        {
                            buttons[i].setText("X");
                            break;
                        }
                    }
                    checkWinner();
                }
                    
                    
             }
    }
}