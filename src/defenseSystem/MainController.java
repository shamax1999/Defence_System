package defenseSystem;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainController extends JFrame implements Observeble{
    
    private final ArrayList<Observer> DefenseComponents;
    boolean sendPrivate = false;
    JTextField text;
    public JLabel displayText;
    JLabel lblAmmoAmount;
    JLabel lblSolderCount;
    JLabel lblFuelAmount;
    JLabel displayAmmoAmount;
    JLabel displaySolderCount;
    JLabel displayFuelAmount;
    JLabel lblPosition;
    public JComboBox cb;
    public JComboBox cb2;
    JSlider positionMeter;
    JCheckBox areaCheck;
    ScrollPane msgPane;
    ScrollPane notifyPane;
    ScrollPane sendMsgPane;
    MyButton btnSend;
    JCheckBox checkPrivate;
    JTextField txtMsg;
    JTextArea lblDisplayMessage;
    JTextArea lblDisplayNotify;
    JTextArea lblDisplayRecieveMessage;
    
    public MainController(){
        DefenseComponents = new ArrayList<>();
        
        setTitle("Main Controller");
        setSize(700,400);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(new Color(54, 69, 74));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(10, 0);
        
        String[] choices = getComponentNames();

        cb = new JComboBox<String>(choices);
        cb.setBounds(20, 20, 150, 30);
        cb.setBackground(Color.white);
        cb.setForeground(Color.black);
        cb.setVisible(true);
        cb.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cb.getSelectedItem() != cb.getItemAt(0)){
                    if(cb.getSelectedItem().toString().startsWith("S")){
                        lblFuelAmount.setText("Oxygen Level");
                    }else{
                        lblFuelAmount.setText("Fuel Amount");
                    }
                    String[] details = getInfo(cb.getSelectedItem().toString());
                    displayFuelAmount.setText(": "+details[1]);
                    displayAmmoAmount.setText(": "+details[2]);
                    displaySolderCount.setText(": "+details[0]);
                }else{
                    displayFuelAmount.setText("");
                    displayAmmoAmount.setText("");
                    displaySolderCount.setText("");
                }
            }
        });
        add(cb);
        
        lblSolderCount = new JLabel("Soldier Count");
        lblSolderCount.setBounds(20, 60, 150, 30);
        lblSolderCount.setBackground(Color.black);
        lblSolderCount.setForeground(Color.white);
        add(lblSolderCount);
        
        displaySolderCount = new JLabel();
        displaySolderCount.setBounds(130, 60, 150, 30);
        displaySolderCount.setBackground(Color.black);
        displaySolderCount.setForeground(Color.pink);
        add(displaySolderCount);
        
        lblFuelAmount = new JLabel("Fuel Amount");
        lblFuelAmount.setBounds(20, 95, 150, 30);
        lblFuelAmount.setBackground(Color.black);
        lblFuelAmount.setForeground(Color.white);
        add(lblFuelAmount);
        
        displayFuelAmount = new JLabel();
        displayFuelAmount.setBounds(130, 95, 150, 30);
        displayFuelAmount.setBackground(Color.black);
        displayFuelAmount.setForeground(Color.pink);
        add(displayFuelAmount);
        
        lblSolderCount = new JLabel("Ammo Count");
        lblSolderCount.setBounds(20, 130, 150, 30);
        lblSolderCount.setBackground(Color.black);
        lblSolderCount.setForeground(Color.white);
        add(lblSolderCount);
        
        displayAmmoAmount = new JLabel();
        displayAmmoAmount.setBounds(130, 130, 150, 30);
        displayAmmoAmount.setBackground(Color.black);
        displayAmmoAmount.setForeground(Color.pink);
        add(displayAmmoAmount);
        
        lblPosition = new JLabel("Position");
        lblPosition.setBounds(20, 165, 150, 30);
        lblPosition.setBackground(Color.black);
        lblPosition.setForeground(Color.white);
        add(lblPosition);
        
        positionMeter = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        positionMeter.setBounds(10, 190, 300, 35);
        positionMeter.setPaintLabels(true);
        positionMeter.setPaintTicks(true);
        positionMeter.setPaintTrack(true);
        positionMeter.setMajorTickSpacing(20);
        positionMeter.setMinorTickSpacing(10);
        positionMeter.setForeground(Color.white);
        positionMeter.setOpaque(false);
        positionMeter.addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(ChangeEvent e) {
                setPosition(positionMeter.getValue());
            }
        });
        add(positionMeter);
        
        areaCheck = new JCheckBox("Area Clear");
        areaCheck.setBounds(220, 20, 100, 30);
        areaCheck.setForeground(Color.white);
        areaCheck.setBackground(new Color(54, 69, 74));
        areaCheck.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                notifyAreaStatus(areaCheck.isSelected());
            }
        });
        add(areaCheck);
        
        lblDisplayNotify = new JTextArea();
        lblDisplayNotify.setBackground(Color.black);
        lblDisplayNotify.setForeground(Color.red);
        notifyPane = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
        notifyPane.setBounds(55, 230, 250, 120);
        notifyPane.add(lblDisplayNotify);
        add(notifyPane);
        
        lblDisplayMessage = new JTextArea();
        lblDisplayMessage.setBackground(Color.black);
        lblDisplayMessage.setForeground(Color.white);
        sendMsgPane = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
        sendMsgPane.setBounds(300, 50, 250, 120);
        sendMsgPane.add(lblDisplayMessage);
        add(sendMsgPane);
        
        lblDisplayRecieveMessage = new JTextArea();
        lblDisplayRecieveMessage.setBackground(Color.black);
        lblDisplayRecieveMessage.setForeground(Color.white);
        msgPane = new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
        msgPane.setBounds(360, 230, 250, 120);
        msgPane.add(lblDisplayRecieveMessage);
        add(msgPane);
        
        checkPrivate = new JCheckBox("Send Private");
        checkPrivate.setBounds(560, 60, 120, 30);
        checkPrivate.setForeground(Color.white);
        checkPrivate.setBackground(new Color(54, 69, 74));
        checkPrivate.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkPrivate.isSelected()){
                    sendPrivate = true;
                    cb2.setEnabled(true);
                    btnSend.setEnabled(false);
                }else{
                    sendPrivate = false;
                    btnSend.setEnabled(true);
                    cb2.setEnabled(false);
                    cb2.setSelectedIndex(0);
                }
            }
        });
        add(checkPrivate);

        cb2 = new JComboBox<String>(choices);
        cb2.setBounds(560, 100, 120, 30);
        cb2.setBackground(Color.white);
        cb2.setForeground(Color.black);
        cb2.setVisible(true);
        cb2.setEnabled(false);
        cb2.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cb2.getSelectedItem() == cb2.getItemAt(0) && sendPrivate == true){
                    btnSend.setEnabled(false);
                }else{
                    btnSend.setEnabled(true);
                }
            }
        });
        add(cb2);
        
        txtMsg = new JTextField();
        txtMsg = new JTextField();
        txtMsg.setBounds(350, 185, 200, 35);
        add(txtMsg);
        
        btnSend = new MyButton();
        btnSend.setText("Send");
        btnSend.setFocusable(false);
        btnSend.setBounds(560, 190, 70, 20);
        btnSend.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sendPrivate == true){
                    if(!txtMsg.getText().equals("")){
                        sentPrivateMessage(txtMsg.getText(), cb2.getSelectedItem().toString());
                        if(lblDisplayMessage.getText().equals("")){
                            lblDisplayMessage.setText("["+cb2.getSelectedItem().toString()+"] :"+txtMsg.getText());
                        }else{
                            lblDisplayMessage.setText(lblDisplayMessage.getText().trim()+"\n["+cb2.getSelectedItem().toString()+"] :"+txtMsg.getText());
                        }  
                    }
                }else{
                    if(!txtMsg.getText().equals("")){
                        sendMessage(txtMsg.getText());
                        if(lblDisplayMessage.getText().equals("")){
                            lblDisplayMessage.setText("[To All] :"+txtMsg.getText());
                        }else{
                            lblDisplayMessage.setText(lblDisplayMessage.getText().trim()+"\n[To All] :"+txtMsg.getText());
                        }  
                    }
                }
                txtMsg.setText("");
            }
        });
        add(btnSend);
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("assets/defence.png")));
        setVisible(true);
    }
    
    
    @Override
    public void addComponent(Observer obj){
        DefenseComponents.add(obj); 
        cb.addItem(obj.getComponentName());
        cb2.addItem(obj.getComponentName());
    }
    
    
    @Override
    public void sendMessage(String msg){
        for(Observer obj : DefenseComponents){
            obj.receiveMessage(msg,false);
        }
    }
    
    
    @Override
    public String[] getComponentNames(){
       String[] components = new String[DefenseComponents.size()+1];
       components[0] = "Select Defense";
        for (int i = 1; i < DefenseComponents.size(); i++) {
            components[i] = DefenseComponents.get(i).getComponentName();
        }
       return components;
    }
    
    
    @Override
    public void notifyAreaStatus(boolean areaState){
        for(Observer obj : DefenseComponents){
            obj.notifyAreaStatus(areaState);
        }
    }
    
    public void setPosition(int value){
        for(Observer obj : DefenseComponents){
            obj.setPosition(value);
        }
    }
    
    
    @Override
    public void sentPrivateMessage(String msg, String name){  
        for(Observer obj : DefenseComponents){
            if(obj.getComponentName().equals(name)){
                obj.receiveMessage(msg,true);
                break;
            }
        } 
    }
    
    public String[] getInfo(String name){
        for(Observer obj : DefenseComponents){
            if(obj.getComponentName().equals(name)){
                String[] arr = obj.getInfo();
                return arr;
            }
        }
        return null;
    }
    
    
    @Override
    public void fuelInfoStatus(String name){
        if(lblDisplayNotify.getText().equals("")){
            lblDisplayNotify.setText("[From "+name+"] : low fuel");
        }else{
            lblDisplayNotify.setText(lblDisplayNotify.getText().trim()+"\n[From "+name+"] : low fuel");
        } 
    }
    
    
    @Override
    public void oxygenInfoStatus(String name){
        if(lblDisplayNotify.getText().equals("")){
            lblDisplayNotify.setText("[From "+name+"] : low oxygen level");
        }else{
            lblDisplayNotify.setText(lblDisplayNotify.getText().trim()+"\n[From "+name+"] : low oxygen level");
        } 
    }
    
    public void energyInfoStatus(String name){
        if(lblDisplayNotify.getText().equals("")){
            lblDisplayNotify.setText("[From "+name+"] : low energy level");
        }else{
            lblDisplayNotify.setText(lblDisplayNotify.getText().trim()+"\n[From "+name+"] : low energy level");
        } 
    }
    
    @Override
    public void damageInfoStatus(String name){
        if(lblDisplayNotify.getText().equals("")){
            lblDisplayNotify.setText("[From "+name+"] : dangerous damage level.");
        }else{
            lblDisplayNotify.setText(lblDisplayNotify.getText().trim()+"\n[From "+name+"] : dangerous damage level.");
        } 
    }
   
    @Override
    public void deathNotice(String name){
        if(lblDisplayNotify.getText().equals("")){
            lblDisplayNotify.setText("[From "+name+"] : destroyed...");
        }else{
            lblDisplayNotify.setText(lblDisplayNotify.getText().trim()+"\n[From "+name+"] : destroyed...");
        } 
    }
    
    public void solderDeathNotice(String name){
        if(lblDisplayNotify.getText().equals("")){
            lblDisplayNotify.setText("[From "+name+"] : all solders are down...");
        }else{
            lblDisplayNotify.setText(lblDisplayNotify.getText().trim()+"\n[From "+name+"] : all solders are down...");
        } 
    }
    
    public void receiveMessageFromObserver(String name, String msg){
        if(lblDisplayRecieveMessage.getText().equals("")){
            lblDisplayRecieveMessage.setText("[From "+name+"] : "+msg);
        }else{
            lblDisplayRecieveMessage.setText(lblDisplayRecieveMessage.getText().trim()+"\n[From "+name+"] : "+msg);
        } 
    }
}
