package ile_interdite.Vue;

import java.awt.Dimension;
import static java.awt.SystemColor.window;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import ile_interdite.util.MessageInscription;
import ile_interdite.util.Utils.Commandes;
/**
 *
 * @author guedona
 */
public class VueInscription extends Observable{
private JFrame window;
   
     private JComboBox jChoice;
     private JPanel panelJoueur;
     private ArrayList<String> pseudos = new ArrayList<>();
     private int difficulte;
     private ArrayList<JTextField> textPseudos = new ArrayList<>();
    /**
     * @param args the command line arguments
     */
     public VueInscription(){
        window = new JFrame("Inscription");
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(500, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        JPanel mainPanel = new JPanel();
        JPanel northPanel = new JPanel();
        JPanel westPanel = new JPanel();
        JPanel eastPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel southPanel = new JPanel();
        window.add(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(westPanel, BorderLayout.WEST);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        
        //panel nord
        northPanel.setLayout(new BorderLayout());
        
        JPanel nbJ = new JPanel();
        
        northPanel.add(nbJ);
        northPanel.add(new JPanel(),BorderLayout.NORTH);
        northPanel.add(new JPanel(),BorderLayout.EAST);
        northPanel.add(new JPanel(),BorderLayout.WEST);
        northPanel.add(new JPanel(),BorderLayout.SOUTH);
        northPanel.add(nbJ,BorderLayout.CENTER);
            // nbJ panel
            JLabel labNbJ = new JLabel("Nombre de joueurs :");
            
            String[] nbJChoice = new String[]{"2","3","4"}; // choix des joueurs
            jChoice = new JComboBox(nbJChoice);
            jChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateNbr();
            }
        });
            nbJ.setLayout(new GridLayout(1,4 ));
            nbJ.add(new JPanel());
            nbJ.add(labNbJ);
            nbJ.add(jChoice);
            nbJ.add(new JPanel());
             
    
// panel ouest new ArrayList());
    JPanel diff = new JPanel();
    diff.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    westPanel.setLayout(new BorderLayout());
    westPanel.add(new JPanel(),BorderLayout.NORTH);
    westPanel.add(new JPanel(),BorderLayout.EAST);
    westPanel.add(new JPanel(),BorderLayout.WEST);
    westPanel.add(new JPanel(),BorderLayout.SOUTH);
    westPanel.add(diff,BorderLayout.CENTER);
        //diff
        diff.setLayout(new GridLayout(5, 1));
        JPanel diffpanel = new JPanel();
        JPanel diff4 = new JPanel();
        JPanel diff3 = new JPanel();
        JPanel diff2 = new JPanel();
        JPanel diff1 = new JPanel();
        //radio buttons
        
        
            //label diff
            diffpanel.setLayout(new BorderLayout());
            diffpanel.add(new JPanel(),BorderLayout.NORTH);
            diffpanel.add(new JPanel(),BorderLayout.EAST);
            diffpanel.add(new JPanel(),BorderLayout.WEST);
            diffpanel.add(new JPanel(),BorderLayout.SOUTH);
            diffpanel.add(new JLabel("Difficulté"),BorderLayout.CENTER);
            //niveau 4
            JPanel centerDiff4 = new JPanel();
            diff4.setLayout(new BorderLayout());
            diff4.add(new JPanel(),BorderLayout.NORTH);
            diff4.add(new JPanel(),BorderLayout.EAST);
            diff4.add(new JPanel(),BorderLayout.WEST);
            diff4.add(new JPanel(),BorderLayout.SOUTH);
            diff4.add(centerDiff4,BorderLayout.CENTER);
                    // centre
                    
                    HashMap<Integer, JRadioButton> radiobuttons ;
                    ButtonGroup groupeDifficulte = new ButtonGroup();
                    radiobuttons = new HashMap<>();
                    
                    centerDiff4.setLayout(new FlowLayout(0));
                    JRadioButton radioButton = new JRadioButton("Légendaire");
                    radiobuttons.put(radiobuttons.size(), radioButton);
                    centerDiff4.add(radioButton);
                    groupeDifficulte.add(radioButton);
                    
                    
             //niveau 3       
            JPanel centerDiff3 = new JPanel();
            diff3.setLayout(new BorderLayout());
            diff3.add(new JPanel(),BorderLayout.NORTH);
            diff3.add(new JPanel(),BorderLayout.EAST);
            diff3.add(new JPanel(),BorderLayout.WEST);
            diff3.add(new JPanel(),BorderLayout.SOUTH);
            diff3.add(centerDiff3,BorderLayout.CENTER);
                    // centre
                    radioButton = new JRadioButton("Elite");
                    centerDiff3.setLayout(new FlowLayout(0));
                    radiobuttons.put(radiobuttons.size(), radioButton);
                    centerDiff3.add(radioButton); 
                    groupeDifficulte.add(radioButton);
             //niveau 2       
            JPanel centerDiff2 = new JPanel();
            diff2.setLayout(new BorderLayout());
            diff2.add(new JPanel(),BorderLayout.NORTH);
            diff2.add(new JPanel(),BorderLayout.EAST);
            diff2.add(new JPanel(),BorderLayout.WEST);
            diff2.add(new JPanel(),BorderLayout.SOUTH);
            diff2.add(centerDiff2,BorderLayout.CENTER);
                    // centre
                    radioButton = new JRadioButton("Normal");
		    radioButton.setSelected(true);
                    centerDiff2.setLayout(new FlowLayout(0));
                    radiobuttons.put(radiobuttons.size(), radioButton);
                    centerDiff2.add(radioButton);
                    groupeDifficulte.add(radioButton);
             //niveau 1       
            JPanel centerDiff1 = new JPanel();
            diff1.setLayout(new BorderLayout());
            diff1.add(new JPanel(),BorderLayout.NORTH);
            diff1.add(new JPanel(),BorderLayout.EAST);
            diff1.add(new JPanel(),BorderLayout.WEST);
            diff1.add(new JPanel(),BorderLayout.SOUTH);
            diff1.add(centerDiff1,BorderLayout.CENTER);
                    // centre
                    radioButton = new JRadioButton("Novice");
                    centerDiff1.setLayout(new FlowLayout(0));
                    radiobuttons.put(radiobuttons.size(), radioButton);
                    centerDiff1.add(radioButton); 
                    radioButton.setAlignmentX(0);
                    groupeDifficulte.add(radioButton);
         // ajout des layouts           

         diff.add(diffpanel);
         diff.add(diff4);
         diff.add(diff3);
         diff.add(diff2);
         diff.add(diff1);
         
    //panel Centre
    panelJoueur = new JPanel();
    centerPanel.setLayout(new BorderLayout());
    centerPanel.add(new JPanel(),BorderLayout.NORTH);
    centerPanel.add(new JPanel(),BorderLayout.EAST);
    centerPanel.add(new JPanel(),BorderLayout.WEST);
    centerPanel.add(new JPanel(),BorderLayout.SOUTH);
    centerPanel.add(panelJoueur,BorderLayout.CENTER);
        //joueurs
        panelJoueur.setLayout(new GridLayout(4, 1));
        buildPlayers();

    //panel sud
    JPanel buttons = new JPanel();
    southPanel.setLayout(new BorderLayout());
    southPanel.add(new JPanel(),BorderLayout.NORTH);
    southPanel.add(new JPanel(),BorderLayout.EAST);
    southPanel.add(new JPanel(),BorderLayout.WEST);
    southPanel.add(new JPanel(),BorderLayout.SOUTH);
    southPanel.add(buttons,BorderLayout.CENTER);
    //bouttons
        JButton help = new JButton("?");//TODO \E9crire la vue des r\E8gles
        JButton play = new JButton("Jouer");
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                for (Integer i : radiobuttons.keySet()){
                	if (radiobuttons.get(i).isSelected()){
                	 difficulte = radiobuttons.keySet().size()-i-1;
                	}
                }
                System.out.println("difficulté : " + difficulte);
                for (int i=0 ; i<jChoice.getSelectedIndex()+2 ;i++){
                	pseudos.add(textPseudos.get(i).getText());
                }
                notifyObservers(new MessageInscription(Commandes.INSCRIPTION,difficulte,pseudos));
                clearChanged();
            }
        });
       buttons.setLayout(new GridLayout(1, 5));
       buttons.add(new JPanel());
       buttons.add(help);
       buttons.add(new JPanel());
       buttons.add(play);
       buttons.add(new JPanel());
       updateNbr();
       
       window.setVisible(true);
       
    }
     
     private void updateNbr(){
         for (int i = 0; i < 4; i++) {
             if (i<(jChoice.getSelectedIndex()+2)){
                 panelJoueur.getComponent(i).setVisible(true);
             }
             else{
                 panelJoueur.getComponent(i).setVisible(false);
             }
         }
     }
     private void buildPlayers(){
         for (int i=0 ; i<4 ; i++){
             JPanel pCJoueur = new JPanel();
             textPseudos.add( new JTextField("joueur"+(i+1)));
             pCJoueur.add(new JLabel("J"+(i+1)+":",JLabel.RIGHT));
             pCJoueur.add(textPseudos.get(i));
             panelJoueur.add(pCJoueur);
         }
                 
     }
     
     public static void main(String[] args)
    {
	VueInscription vue = new VueInscription();
	
    }

    public JFrame getWindow()
    {
	return window;
    }
}