package ynov.reseau.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre extends JFrame {
	
	JTextField gauche= new JTextField(),
			droite = new JTextField(),
			adresseIPV4 = new JTextField(),
			adresseReseau = new JTextField(),
			premiereAdresseHote = new JTextField(),
			derniereAdresseHote = new JTextField(),
			adresseDiffusion = new JTextField(),
			nbrTtlBitHote = new JTextField(),
			nbrTtlMachine = new JTextField(),
			slash = new JTextField(),
			jtfsubnetMask = new JTextField();
	
	JLabel gauchel= new JLabel(),
			droitel = new JLabel(),
			adresseIPV4l = new JLabel("IPV4:"),
			adresseReseaul = new JLabel("Adresse Reseau"),
			adresseDiffusionl = new JLabel("Adresse de diffusion"),
			premiereAdresseHotel = new JLabel("Premiere adresse d'hote"),
			derniereAdresseHotel = new JLabel("Derniere adresse d'hote"),
			nbrTtlBitHotel = new JLabel("Nombre total de bits hotes"),
			nbrTtlMachinel = new JLabel("Nombre total d'hotes"),
			slashl = new JLabel("/"),
			subnetMaskl = new JLabel("Subnet Mask");
	
	JPanel hautPanel = new JPanel(),
			basPanel = new JPanel();
	
	JButton hautButton = new JButton("Executer"),
			basButton = new JButton("Executer");

	
	
	String [] choixmultiples = new String[]{"bin vers dec", "dec vers bin"};
	
	JComboBox<String> choix=new JComboBox<String>(choixmultiples);
	

	
	
	public Fenetre(){
		this.setTitle("Calculateur Reseau");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(500,250));
		this.setMinimumSize(new Dimension(500,250));
		this.setLayout(new GridLayout(10,2));
		this.add(hautPanel);
		hautPanel.setLayout(new BoxLayout(hautPanel, BoxLayout.LINE_AXIS));
		
		hautPanel.add(gauche);
		hautPanel.add(choix);
		hautPanel.add(droite);
		this.add(hautButton);
		this.add(adresseIPV4l);
		basPanel.setLayout(new BoxLayout(basPanel, BoxLayout.LINE_AXIS));
		basPanel.add(adresseIPV4);
		basPanel.add(slashl);
		basPanel.add(slash);
		this.add(basPanel);
		this.add(subnetMaskl);
		this.add(jtfsubnetMask);
		this.add(adresseReseaul);
		this.add(adresseReseau);
		this.add(premiereAdresseHotel);
		this.add(premiereAdresseHote);
		this.add(derniereAdresseHotel);
		this.add(derniereAdresseHote);
		this.add(adresseDiffusionl);
		this.add(adresseDiffusion);
		this.add(nbrTtlBitHotel);
		this.add(nbrTtlBitHote);
		this.add(nbrTtlMachinel);
		this.add(nbrTtlMachine);
		this.add(basButton);
		
		hautButton.addActionListener(new BoutonConvListener());
		basButton.addActionListener(new BoutonTabListener());
		this.setVisible(true);
		
	}
	
	private class BoutonConvListener implements ActionListener{
	    //Red�finition de la m�thode actionPerformed()
	    public void actionPerformed(ActionEvent arg0) {
	    	String jtfGauche= gauche.getText();
	    	String resultat = null;
	    	int resultatInt;
			
			
			if(choix.getSelectedItem()==choixmultiples[0] && jtfGauche!=null &&jtfGauche.length()>0){//bin vers dec
				try{
				resultatInt = Integer.parseInt(jtfGauche, 2);
				resultat=Integer.toString(resultatInt);
				System.out.println("lol");
				}
				catch(Exception e){
					
					JOptionPane.showMessageDialog(null, "champ mal rempli", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			
			else if(choix.getSelectedItem()!=null && jtfGauche.length()>0){//dec vers bin
				try{
					resultatInt=Integer.parseInt(jtfGauche.trim());
					resultat=Integer.toBinaryString(resultatInt);
			}
			catch(Exception e){
				
				JOptionPane.showMessageDialog(null, "champ mal rempli", "Error", JOptionPane.ERROR_MESSAGE);
			}
			}
			else{
				
				JOptionPane.showMessageDialog(null, "champ vide", "Error", JOptionPane.ERROR_MESSAGE);
			}
			droite.setText(resultat);
	    }
	   
	  }
	
	private class BoutonTabListener implements ActionListener{
	    //Red�finition de la m�thode actionPerformed()
	    public void actionPerformed(ActionEvent arg0) {
	    try{
	    	setTextWithSubnetAndIP(adresseIPV4.getText(),slash.getText());
	    }
	    catch(Exception e){
			
			JOptionPane.showMessageDialog(null, "champ mal rempli", "Error", JOptionPane.ERROR_MESSAGE);
		}
	    int slashInt =Integer.parseInt(slash.getText().trim());
	    
	    createButtonField(slashInt);
	    
		}

		
	}
	public String[] setTextWithSubnetAndIP(String ip, String subnetMask){
		System.out.println(ip);
		String[] ipString = ip.split("\\.");
		for(String s : ipString)
			System.out.println(s);
		int [] ipInt= new int[4];
		for(int i=0;i<ipString.length;i++)
			ipInt[i]=Integer.parseInt(ipString[i].trim());
		
	    int maskI = Integer.parseInt(subnetMask);//mise du subnet en nombre
	    subnetMask="";
	    for(int i=0; i<32;i++){//fabrication du subnet mask en binaire avec espace
	    	if((i)%8==0 && i>0){
	    		subnetMask+=" ";
	    	}
	    	if(i<maskI){
	    		subnetMask+="1";
	    	}
	    	else{
	    		subnetMask+="0";
	    	}
	    
	    }
	  //tete du subnet 11111111 11111111 11111111 00000000
	    String[] maskTab =subnetMask.split(" ");//decoupe du subnet et mise dans un tableau de chaque octet
	  //tete du subnet array[0]=11111111 array[1]=11111111 array[2]=11111111 array[3]=00000000
	    String[] binary0=maskTab[0].split("");
	    String[] binary1=maskTab[1].split("");
	    String[] binary2=maskTab[2].split("");
	    String[] binary3=maskTab[3].split("");

	    int[] octetInt= {0,0,0,0};
	    
	    for(int j=0;j<8;j++)
	    	octetInt[0]+=(int) Integer.parseInt(binary0[j].trim())*Math.pow(2,7-j);
	    for(int k=0;k<8;k++)
	    	octetInt[1]+=(int) Integer.parseInt(binary1[k].trim())*Math.pow(2,7-k);
	    for(int l=0;l<8;l++)
	    	octetInt[2]+=(int) Integer.parseInt(binary2[l].trim())*Math.pow(2,7-l);
    	for(int m=0;m<8;m++)
	    	octetInt[3]+=(int) Integer.parseInt(binary3[m].trim())*Math.pow(2,7-m);
	    String subnetMask1=octetInt[0]+"."+octetInt[1]+"."+octetInt[2]+"."+octetInt[3];//initialisation du subnet
	    
	    
	    int i=0;

	    
	    String[] ipSTab= ip.split("\\.");

	    //maskInt=Integer.parseInt(subnetMask1.trim());
	    int[] adresseReseauInt = new int[4];
	    for(int j=0;i<4;i++){
	    	adresseReseauInt[i]=ipInt[i]&octetInt[i];
	    }
		    adresseReseau.setText(adresseReseauInt[0]+"."+adresseReseauInt[1]+"."+adresseReseauInt[2]+"."+adresseReseauInt[3]);
		    int[] adresseDiffusionTab= adressDiffusion(adresseReseauInt,octetInt);
		    adresseDiffusion.setText(adresseDiffusionTab[0]+"."+adresseDiffusionTab[1]+"."+adresseDiffusionTab[2]+"."+adresseDiffusionTab[3]);
		    nbrTtlBitHote.setText(Integer.toString(32-maskI));
		    nbrTtlMachine.setText(Integer.toString((int) (Math.pow(2,(32-maskI))-2)));
		    jtfsubnetMask.setText(subnetMask1);
		    premiereAdresseHote.setText(adresseReseauInt[0]+"."+adresseReseauInt[1]+"."+adresseReseauInt[2]+"."+(adresseReseauInt[3]+1));
		    derniereAdresseHote.setText(adresseDiffusionTab[0]+"."+adresseDiffusionTab[1]+"."+adresseDiffusionTab[2]+"."+(adresseDiffusionTab[3]-1));

	    return null;
		
	}
	
	
	public int[] adressDiffusion(int[] ip, int[] mask )	{
		String a="";
		int[] maskInverse = new int[4];

		String[] resultat=new String[4];
		String[] resultatSplit1 =new String[4];
		String[] resultatSplit2 =new String[4];
		String[] resultatSplit3 =new String[4];
		String[] resultatSplit4 =new String[4];
		for(int i=0;i<4;i++){
			resultat[i]=Integer.toBinaryString(mask[i]);
			if(resultat[i].length()==1)
				resultat[i]="0000000"+resultat[i];
			if(resultat[i].length()==2)
				resultat[i]="000000"+resultat[i];
			else if(resultat[i].length()==3)
				resultat[i]="00000"+resultat[i];
			else if(resultat[i].length()==4)
				resultat[i]="0000"+resultat[i];
			else if(resultat[i].length()==5)
				resultat[i]="000"+resultat[i];
			else if(resultat[i].length()==6)
				resultat[i]="00"+resultat[i];
			else if(resultat[i].length()==7)
				resultat[i]="0"+resultat[i];
		}
		
		resultatSplit1 =resultat[0].split("");
		resultatSplit2 =resultat[1].split("");
		resultatSplit3 =resultat[2].split("");
		resultatSplit4 =resultat[3].split("");
		
		for(int i=0;i<8;i++){
			if(resultatSplit1[i].compareTo("0")==0)
				resultatSplit1[i]="1";
			else
				resultatSplit1[i]="0";
			
			if(resultatSplit2[i].compareTo("0")==0)
				resultatSplit2[i]="1";
			else
				resultatSplit2[i]="0";
			
			if(resultatSplit3[i].compareTo("0")==0)
				resultatSplit3[i]="1";
			else
				resultatSplit3[i]="0";
			if(resultatSplit4[i].compareTo("0")==0)
				resultatSplit4[i]="1";
			else
				resultatSplit4[i]="0";
			
		}
		for(int i=0;i<4;i++){
			resultat[i]="";
		}
		
			for(int i=0;i<8;i++){
				resultat[0]+=resultatSplit1[i];
				resultat[1]+=resultatSplit2[i];
				resultat[2]+=resultatSplit3[i];
				resultat[3]+=resultatSplit4[i];
			}
		int[] resultatInt = new int[4];
		for(int i=0;i<4;i++){
			resultatInt[i] = (int) Integer.parseInt(resultat[i], 2);

			resultat[i]=Integer.toString(resultatInt[i]);
			resultatInt[i]=Integer.parseInt(resultat[i].trim());
		}
		int[] tableauFinal = new int[4];
		for(int i=0;i<4;i++){
			tableauFinal[i]=ip[i]|resultatInt[i];
		}
			
		return (tableauFinal);
	  /*si bit(n,i)=1 alors
	    positionner bit(m,n-1-i)
	  fin si
	fin pour*/
	
	}
	private void createButtonField(int slashInt) {
		// TODO Auto-generated method stub
		
		
	}
}


