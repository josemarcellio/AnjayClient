package com.josemarcellio;

import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.modules.combat.BowAimbot;
import net.ccbluex.liquidbounce.features.module.modules.combat.KillAura;
import net.ccbluex.liquidbounce.features.module.modules.combat.Velocity;
import net.ccbluex.liquidbounce.features.module.modules.movement.Fly;
import net.ccbluex.liquidbounce.features.module.modules.player.AntiCheatDebugger;
import net.ccbluex.liquidbounce.features.module.modules.player.Reach;
import net.ccbluex.liquidbounce.features.module.modules.world.Scaffold;
import net.ccbluex.liquidbounce.file.FileManager;
import net.ccbluex.liquidbounce.ui.client.clickgui.ClickGui;
import net.ccbluex.liquidbounce.utils.ClientUtils;
import org.lwjgl.opengl.Display;
import net.ccbluex.liquidbounce.ui.client.hud.HUD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InjectorClient extends JFrame implements ActionListener {
    JLabel l;                       //label
    JTextField TextF, TextF2;       //TextFiled
    JButton btn, btn2;              // button
    JCheckBox killaura,reach, scaffold, velocity, fly, anticheatdebug;
    public FileManager fileManager;
    public ClickGui clickGui;
    public HUD hud;

    // GUI

    public void setDisplay() throws IOException {
        Display.setTitle ( "AnjayClient" );
    }

    public InjectorClient() {
        super ( "Jose Client Helper" );          // title of Frame passing using  ' super '

        killaura = new JCheckBox ( "Kill Aura" );       // Label
        killaura.setBounds ( 15, 8, 110, 20 );        // set the Boundry of label
        killaura.setOpaque(false);

        reach = new JCheckBox ("Reach");
        reach.setBounds ( 115, 8, 120, 20 );
        reach.setOpaque(false);

        scaffold = new JCheckBox ("Scaffold");
        scaffold.setBounds ( 15, 33, 130, 20 );
        scaffold.setOpaque(false);

        velocity = new JCheckBox ("Velocity");
        velocity.setBounds ( 115, 33, 140, 20 );
        velocity.setOpaque(false);

        fly = new JCheckBox ("Fly");
        fly.setBounds ( 15, 58, 150, 20 );
        fly.setOpaque(false);

        anticheatdebug = new JCheckBox ("Anti Cheat Debugger");
        anticheatdebug.setBounds ( 115, 58, 160, 20 );
        anticheatdebug.setOpaque(false);

        btn = new JButton ("Set");
        btn.setBounds ( 53, 90, 170, 20 );
        // implement the ActionListener on the button
        btn.addActionListener ( this );
        //b.addActionListener(this);


        //adding textField, label and button in frame
        add ( killaura );
        add ( reach );
        add(scaffold);
        add(velocity);
        add(fly);
        add(anticheatdebug);
        add (btn);


        //Frame visibility setting
        setSize ( 283, 150 );                          // set the Frame Size width and height
        setLayout ( null );                          //hide default layout of frame
        setVisible ( true );                        //set the visisbility of frame
        setBackground ( Color.GRAY );               //set background color of frame
        setResizable ( false );
        setLocationRelativeTo ( null );
    }

    Module killaura2 = LiquidBounce.INSTANCE.getModuleManager().get( KillAura.class);
    Module reach2 = LiquidBounce.INSTANCE.getModuleManager().get( Reach.class);
    Module scaffold2 = LiquidBounce.INSTANCE.getModuleManager().get( Scaffold.class);
    Module velocity2 = LiquidBounce.INSTANCE.getModuleManager().get( Velocity.class);
    Module fly2 = LiquidBounce.INSTANCE.getModuleManager().get( Fly.class);
    Module anticheatdebug2 = LiquidBounce.INSTANCE.getModuleManager().get( AntiCheatDebugger.class);
    //handle the actionEvent using ' actionPerformed(ActionEvent e) '
    public void actionPerformed(ActionEvent e) {
        if(killaura.isSelected()) {
            killaura2.setState ( true);
        } else {
            killaura2.setState ( false);
        }
        if(reach.isSelected()) {
            reach2.setState(true);
        } else {
            reach2.setState ( false);
        }
        if(scaffold.isSelected()) {
            scaffold2.setState(true);
        } else {
            scaffold2.setState ( false);
        }
        if(velocity.isSelected()) {
            velocity2.setState(true);
        } else {
            velocity2.setState ( false);
        }
        if(fly.isSelected()) {
            fly2.setState(true);
        } else {
            fly2.setState ( false);
        }
        if(anticheatdebug.isSelected()) {
            anticheatdebug2.setState(true);
        } else {
            anticheatdebug2.setState ( false);
        }
    }


        public static void main(String args[])
    {
        InjectorClient obj = new InjectorClient();
    }
}