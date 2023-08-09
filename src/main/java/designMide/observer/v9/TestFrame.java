package designMide.observer.v9;

import com.mashibing.tank.TankFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestFrame extends Frame {
    public void launch(){
        Button b = new Button("press me");
        b.addActionListener(new MyActionListener());
        b.addActionListener(new MyActionListener2());
        this.add(b);
        this.pack();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.setLocation(400,400);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        TestFrame tankFrame = new TestFrame();
        tankFrame.launch();
    }

    private class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ((Button)e.getSource()).setLabel("button press again");
            System.out.println("button pressed");
        }
    }

    private class MyActionListener2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("button pressed 2");
        }
    }
}

