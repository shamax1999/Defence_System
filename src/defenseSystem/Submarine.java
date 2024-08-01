package defenseSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Submarine extends JFrame implements Observer{
    boolean position = false;
    int positionValue = 0;
    int fuelAmount = 100;
    int energyAmount = 100;
    int damageAmount = 0;
    boolean increase = false;
    boolean increaseEnergy = false;
    boolean increaseDamage = false;
    boolean increaseDeath = false;
    boolean decreaseOxygen = false;
     boolean decreaseEnergy = false;
    
    public JLabel displayText;
    JTextField text;
    public JLabel lblAreaStatus;
    JLabel lblFuel;
    JLabel lblEnergy;
    JLabel lblAmmoCount;
    JLabel lblSolderCount;
    JLabel lblWarning;
    MyButton btnShoot;
    MyButton btnMissile;
    MyButton btnRedar;
    MyButton btnRotateShooting;
    JSlider fuelMeter;
    JSlider energyMeter;
    JSpinner ammoAmount;
    JSpinner solderAmount;
    JTextField txtMessage;
    MyButton btnSend;
    JPanel imagePanel;
    ImageIcon image;
    JButton btnTest;
    ScrollPane msgPane;
    JCheckBox setPosition;
    public JTextArea lblDisplayMessage;
    
    public Submarine(MainController obj){
        setTitle("Submarine");
        setSize(650,400);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(new Color(54, 69, 74));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(700, 400);
        
        lblAreaStatus = new JLabel();
        lblAreaStatus.setForeground(Color.yellow);
        lblAreaStatus.setBounds(10, 10, 100, 30);
        add(lblAreaStatus);
        
        lblWarning = new JLabel("DANGER");
        lblWarning.setFont(new Font("bold", Font.BOLD, 30));
        lblWarning.setForeground(Color.red);
        lblWarning.setBounds(125, 7, 200, 40);
        lblWarning.setVisible(false);
        add(lblWarning);
        
        lblFuel = new JLabel("Oxygen");
        lblFuel.setBounds(570, 10, 60, 30);
        lblFuel.setForeground(Color.green);
        lblFuel.setHorizontalAlignment(JLabel.CENTER);
        lblFuel.setVerticalAlignment(JLabel.CENTER);
        lblFuel.setBorder(BorderFactory.createLineBorder(Color.green));
        add(lblFuel);
        
        lblEnergy = new JLabel("Energy");
        lblEnergy.setBounds(480, 10, 60, 30);
        lblEnergy.setForeground(Color.green);
        lblEnergy.setHorizontalAlignment(JLabel.CENTER);
        lblEnergy.setVerticalAlignment(JLabel.CENTER);
        lblEnergy.setBorder(BorderFactory.createLineBorder(Color.green));
        add(lblEnergy);
        
        
        fuelMeter = new JSlider(JSlider.VERTICAL, 0, 100, 100);
        fuelMeter.setBounds(570, 50, 60, 300);
        fuelMeter.setPaintLabels(true);
        fuelMeter.setPaintTicks(true);
        fuelMeter.setPaintTrack(true);
        fuelMeter.setMajorTickSpacing(20);
        fuelMeter.setMinorTickSpacing(10);
        fuelMeter.setForeground(Color.white);
        fuelMeter.setOpaque(false);
        fuelMeter.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                fuelAmount = fuelMeter.getValue();
                if(fuelAmount == 20){
                    increase = !increase;
                }
                if(fuelAmount == 20 && increase){
                    sendOxygenStatus(obj);
                }
                if(fuelAmount < 20){
                    lblFuel.setForeground(Color.red);
                    lblFuel.setForeground(Color.red);
                    lblFuel.setBorder(BorderFactory.createLineBorder(Color.red));
                    lblWarning.setVisible(true);
                }else{
                    lblFuel.setForeground(Color.green);
                    lblFuel.setForeground(Color.green);
                    lblFuel.setBorder(BorderFactory.createLineBorder(Color.green));
                    lblWarning.setVisible(false);
                }
                
                if(fuelMeter.getValue() == 1){
                    decreaseOxygen = !decreaseOxygen;
                }
                if(fuelMeter.getValue() == 1 && decreaseOxygen){
                    deathNotice(obj);
                }
                if(fuelMeter.getValue() < 1){
                    btnRotateShooting.setEnabled(false);
                    btnShoot.setEnabled(false);
                    btnMissile.setEnabled(false);
                    btnRedar.setEnabled(false);
                    position = false;
                    setPosition.setSelected(false);
                    btnSend.setVisible(false);
                    imagePanel.setVisible(false);
                }else{
                    btnSend.setVisible(true);
                    imagePanel.setVisible(true);
                }
            }
        });
        add(fuelMeter);
        
        
        energyMeter = new JSlider(JSlider.VERTICAL, 0, 100, 100);
        energyMeter.setBounds(480, 50, 60, 300);
        energyMeter.setPaintLabels(true);
        energyMeter.setPaintTicks(true);
        energyMeter.setPaintTrack(true);
        energyMeter.setMajorTickSpacing(20);
        energyMeter.setMinorTickSpacing(10);
        energyMeter.setForeground(Color.white);
        energyMeter.setOpaque(false);
        energyMeter.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                energyAmount = energyMeter.getValue();
                if(energyAmount == 20){
                    increaseEnergy = !increaseEnergy;
                }
                if(energyAmount == 20 && increaseEnergy){
                    sendEnergyStatus(obj);
                }
                if(energyAmount < 20){
                    lblEnergy.setForeground(Color.red);
                    lblEnergy.setForeground(Color.red);
                    lblEnergy.setBorder(BorderFactory.createLineBorder(Color.red));
                    lblWarning.setVisible(true);
                }else{
                    lblEnergy.setForeground(Color.green);
                    lblEnergy.setForeground(Color.green);
                    lblEnergy.setBorder(BorderFactory.createLineBorder(Color.green));
                    lblWarning.setVisible(false);
                }
                
                if(energyMeter.getValue() == 1){
                    decreaseEnergy = !decreaseEnergy;
                }
                if(energyMeter.getValue() == 1 && decreaseEnergy){
                    deathNotice(obj);
                }
                if(energyMeter.getValue() < 1){
                    btnRotateShooting.setEnabled(false);
                    btnShoot.setEnabled(false);
                    btnMissile.setEnabled(false);
                    btnRedar.setEnabled(false);
                    position = false;
                    setPosition.setSelected(false);
                    btnSend.setVisible(false);
                    imagePanel.setVisible(false);
                }else{
                    btnSend.setVisible(true);
                    imagePanel.setVisible(true);
                }
            }
        });
        add(energyMeter);
        
        
        
        btnShoot = new MyButton();
        btnShoot.setText("Shoot");
        btnShoot.setBounds(10, 50, 150, 30);
        btnShoot.setFocusable(false);
        btnShoot.setEnabled(false);
        btnShoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ammoAmount.setValue((Integer)ammoAmount.getValue()-2);
            }
        });
        add(btnShoot);
        
        btnMissile = new MyButton();
        btnMissile.setText("Sonar Operation");
        btnMissile.setBounds(170, 50, 150, 30);
        btnMissile.setFocusable(false);
        btnMissile.setEnabled(false);
        add(btnMissile);
        
        btnRedar = new MyButton();
        btnRedar.setText("Tomahawk Missile");
        btnRedar.setBounds(10, 90, 150, 30);
        btnRedar.setFocusable(false);
        btnRedar.setEnabled(false);
        add(btnRedar);
        
        btnRotateShooting = new MyButton();
        btnRotateShooting.setText("Trident-2 Missile");
        btnRotateShooting.setBounds(170, 90, 150, 30);
        btnRotateShooting.setFocusable(false);
        btnRotateShooting.setEnabled(false);
        add(btnRotateShooting);
        
        lblSolderCount = new JLabel("Soldier Count");
        lblSolderCount.setBounds(330, 15, 100, 30);
        lblSolderCount.setForeground(Color.white);
        add(lblSolderCount);
        
        setPosition = new JCheckBox("Position");
        setPosition.setBounds(330, 87, 100, 30);
        setPosition.setForeground(Color.white);
        setPosition.setBackground(new Color(54, 69, 74));
        setPosition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendPositionNotification(obj);
                if(setPosition.isSelected()){
                    position = true;
                    setPositionButton();
                }else{
                    position = false;
                    btnRotateShooting.setEnabled(false);
                    btnShoot.setEnabled(false);
                    btnMissile.setEnabled(false);
                    btnRedar.setEnabled(false);
                }
            }
        });
        add(setPosition);
        
        solderAmount = new JSpinner(new SpinnerNumberModel(7,0,10,1));
        solderAmount.setBounds(420, 17, 50, 20);
        solderAmount.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if((Integer)solderAmount.getValue() == 0){
                    solderDeathNotice(obj);
                    btnRotateShooting.setEnabled(false);
                    btnShoot.setEnabled(false);
                    btnMissile.setEnabled(false);
                    btnRedar.setEnabled(false);
                    
                    btnSend.setEnabled(false);
                    imagePanel.setVisible(false);
                    setPosition.setSelected(false);
                    position = false;
                }else{
                    btnSend.setEnabled(true);
                    imagePanel.setVisible(true);
                }
            }
        });
        add(solderAmount);
        
        lblAmmoCount = new JLabel("Ammo Count");
        lblAmmoCount.setBounds(330, 50, 100, 30);
        lblAmmoCount.setForeground(Color.white);
        add(lblAmmoCount);
        
        ammoAmount = new JSpinner(new SpinnerNumberModel(5000,0,5000,1));
        ammoAmount.setBounds(420, 55, 50, 20);
        ammoAmount.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               if((Integer)ammoAmount.getValue() < 500){
                   lblAmmoCount.setForeground(Color.red);
                   lblWarning.setVisible(true);
               }else{
                   lblAmmoCount.setForeground(Color.white);
                   lblWarning.setVisible(false);
               }
            }
        });
        add(ammoAmount);
        
        lblDisplayMessage = new JTextArea();
        lblDisplayMessage.setBackground(Color.black);
        lblDisplayMessage.setForeground(Color.white);
        msgPane = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
        msgPane.add(lblDisplayMessage);
        msgPane.setBounds(250, 130, 200, 150);
        add(msgPane);
        
        txtMessage = new JTextField();
        txtMessage.setBounds(250, 290, 150, 30);
        add(txtMessage);
        
        btnSend = new MyButton();
        btnSend.setText("Send");
        btnSend.setFocusable(false);
        btnSend.setBounds(410, 295, 70, 20);
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessageToMain(obj);
            }
        });
        add(btnSend);
        
        imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBounds(20, 140, 200, 200);
        image = new ImageIcon(getClass().getResource("assets/submarine.png"));
        imagePanel.add(new JLabel(image));
        imagePanel.setBackground(new Color(54, 69, 74));
        add(imagePanel);
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("assets/defence.png")));
        setVisible(true);
    }
    
    @Override
    public void receiveMessage(String msg, boolean isPrivate){
        if(isPrivate == true){
            if(lblDisplayMessage.getText().equals("")){
                lblDisplayMessage.setText("MC :[ Direct ] "+msg);
            }else{
                lblDisplayMessage.setText(lblDisplayMessage.getText().trim()+"\nMC :[ Direct ] "+msg);
            }
        }else{
            if(lblDisplayMessage.getText().equals("")){
                lblDisplayMessage.setText("MC :"+msg);
            }else{
                lblDisplayMessage.setText(lblDisplayMessage.getText().trim()+"\nMC :"+msg);
            }
        }   
    }

    @Override
    public String getComponentName(){
        return "Submarine";
    }
    
    @Override
    public void notifyAreaStatus(boolean obj){
        if(obj == true){
            lblAreaStatus.setText("Area Clear");
            lblAreaStatus.setForeground(Color.yellow);
        }else{
            lblAreaStatus.setText("Area Not Clear");
            lblAreaStatus.setForeground(Color.red);
        }   
    }
    @Override
    public void setPosition(int value){
        positionValue = value;
        if(position == true){
            setPositionButton();
        }else{
            btnRotateShooting.setEnabled(false);
            btnShoot.setEnabled(false);
            btnMissile.setEnabled(false);
            btnRedar.setEnabled(false);
        }
    }
    
    public void setPositionButton(){
        if(damageAmount < 98){
            if(positionValue>Strength.LOW.value){
               btnRotateShooting.setEnabled(false);
               btnShoot.setEnabled(true);
               btnMissile.setEnabled(false);
               btnRedar.setEnabled(false);
            }
            if(positionValue>Strength.MEDIUM.value){
               btnRotateShooting.setEnabled(false);
               btnShoot.setEnabled(true);
               btnMissile.setEnabled(true);
               btnRedar.setEnabled(false);
            }
            if(positionValue>Strength.STRONG.value){
               btnRotateShooting.setEnabled(false);
               btnShoot.setEnabled(true);
               btnMissile.setEnabled(true);
               btnRedar.setEnabled(true);
            }
            if(positionValue>Strength.CLOSED.value){
               btnRotateShooting.setEnabled(true);
               btnShoot.setEnabled(true);
               btnMissile.setEnabled(true);
               btnRedar.setEnabled(true);
            }
            if(positionValue < Strength.LOW.value){
               btnRotateShooting.setEnabled(false);
               btnShoot.setEnabled(false);
               btnMissile.setEnabled(false);
               btnRedar.setEnabled(false);
            }
        }
    }
    
    @Override
    public String[] getInfo(){
        String[] arr = new String[3];
        arr[0] = solderAmount.getValue().toString();
        arr[1] = Integer.toString(fuelAmount);
        arr[2] = ammoAmount.getValue().toString();
        
        return arr;
    }
    
    public void sendOxygenStatus(MainController obj){
          obj.oxygenInfoStatus("Submarine ");
    }
    
    public void sendEnergyStatus(MainController obj){
          obj.energyInfoStatus("Submarine ");
    }
       
    public void deathNotice(MainController obj){
        obj.deathNotice("Submarine ");
    }
    
    public void solderDeathNotice(MainController obj){
        obj.solderDeathNotice("Submarine ");
    }
    
    public void sendMessageToMain(MainController obj){
        if(!txtMessage.getText().equals("")){
            obj.receiveMessageFromObserver(("Submarine "), txtMessage.getText());
            if(lblDisplayMessage.getText().equals("")){
                lblDisplayMessage.setText("[To MC]: "+txtMessage.getText());
            }else{
                lblDisplayMessage.setText(lblDisplayMessage.getText().trim()+"\n[To MC]: "+txtMessage.getText());
            }
            txtMessage.setText("");
        }
    }
    
    public void sendPositionNotification(MainController obj){
        if(setPosition.isSelected()){
            if(obj.lblDisplayNotify.getText().equals("")){
                obj.lblDisplayNotify.setText("Submarine is positioned...");
            }else{
                obj.lblDisplayNotify.setText(obj.lblDisplayNotify.getText().trim()+ "\nSubmarine is positioned...");
            }
        }else{
            obj.lblDisplayNotify.setText(obj.lblDisplayNotify.getText().trim()+ "\nSubmarine is not positioned...");
        }
    }
}