

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Islandbis extends JPanel {
	
	
	private JFrame frame;
	
	private Image mersable0000Sprite;
	private Image mersable0001Sprite;                                                
	private Image mersable0010Sprite;                                                
	private Image mersable0011Sprite;                                                
	private Image mersable0100Sprite;                                                
	private Image mersable0101Sprite;                                                
	private Image mersable0110Sprite;                                                
	private Image mersable0111Sprite;                                                
	private Image mersable1000Sprite;
	private Image mersable1001Sprite; 
	private Image mersable1010Sprite; 
	private Image mersable1011Sprite; 
	private Image mersable1100Sprite; 
	private Image mersable1101Sprite; 
	private Image mersable1110Sprite; 
	private Image mersable1111Sprite; 

	private int spriteLength = 13;

	private int largeur      = 50;
	private int hauteur      = 50;
	private int leftborder   = 10;
	private int rightborder  = 40;
	private int bottomborder = 40;
	private int topborder    = 10; 

	private double pmax = 0.7;
	private double proba_terre, dist;
	private double dmax = Math.max(largeur/2,hauteur/2);

	private int[][] myWorld;
	private int[][] myBorder;

	private int I, J;
	private int code_brique;
	
	public Islandbis()
	{
		try
		{
			mersable0000Sprite = ImageIO.read(new File("mer-sable-0000.png"));
			mersable0001Sprite = ImageIO.read(new File("mer-sable-0001.png"));
			mersable0010Sprite = ImageIO.read(new File("mer-sable-0010.png"));
			mersable0011Sprite = ImageIO.read(new File("mer-sable-0011.png"));
			mersable0100Sprite = ImageIO.read(new File("mer-sable-0100.png"));
			mersable0101Sprite = ImageIO.read(new File("mer-sable-0101.png"));
			mersable0110Sprite = ImageIO.read(new File("mer-sable-0110.png"));
			mersable0111Sprite = ImageIO.read(new File("mer-sable-0111.png"));
			mersable1000Sprite = ImageIO.read(new File("mer-sable-1000.png"));
			mersable1001Sprite = ImageIO.read(new File("mer-sable-1001.png"));
			mersable1010Sprite = ImageIO.read(new File("mer-sable-1010.png"));
			mersable1011Sprite = ImageIO.read(new File("mer-sable-1011.png"));
			mersable1100Sprite = ImageIO.read(new File("mer-sable-1100.png"));
			mersable1101Sprite = ImageIO.read(new File("mer-sable-1101.png"));
			mersable1110Sprite = ImageIO.read(new File("mer-sable-1110.png"));
			mersable1111Sprite = ImageIO.read(new File("mer-sable-1111.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		
		frame = new JFrame("Super island !!!");
		frame.add(this);
		frame.setSize(700,700);
		frame.setVisible(true);
		

		myWorld  = new int[largeur][hauteur]; //briques
		myBorder = new int[largeur+1][hauteur+1]; //intersections
		

		for (int i=0; i==hauteur; i++)
			for (int j=0 ; j==largeur ; j++)
				myBorder[i][j] = 0;



		/* Croix distribuées au hasard à l'intérieur sauf dans la région centrale */
		//on ne veut pas de terre aux extrémités de la carte, donc on commence à 1
		//et on termine à largeur-1	
		for (int i=1 ; i != largeur ; i++){
			for(int j=1 ; j != hauteur ; j++)
			{
				//si on est en dehors du rectangle central
				if ((i<leftborder)||(i>rightborder)||(j>bottomborder)||(j<topborder))
				{
					dist = Math.max(Math.abs(i-largeur/2), Math.abs(j-hauteur/2));
					proba_terre = pmax*Math.sqrt(1-dist/dmax);
					//proba_terre = pmax*Math.pow((1-dist/dmax), 1/2);
					if (Math.random()<proba_terre)
						myBorder[i][j] = 1;
				}
				//si on est dans le rectangle central
				else
					myBorder[i][j] = 1;
			}
		}


		/* Constitution des briques */ 
		for (int i=0 ; i != largeur ; i++)
			for (int j=0 ; j != hauteur ; j++)
			{
				I = 2*i + 1;
				J = 2*j + 1;
				
				code_brique =  2*2*2*myBorder[(int)((I - 1)/2)][(int)((J + 1)/2)];
				code_brique +=   2*2*myBorder[(int)((I - 1)/2)][(int)((J - 1)/2)];
				code_brique +=     2*myBorder[(int)((I + 1)/2)][(int)((J - 1)/2)];
				code_brique +=       myBorder[(int)((I + 1)/2)][(int)((J + 1)/2)];
				
				myWorld[i][j] = code_brique;
			}
	}
	



	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		for (int i=0 ; i < myWorld.length ; i++)
			for (int j=0 ; j < myWorld[0].length ; j++)
			{
				switch(myWorld[i][j])
				{
					case 0 : 
						g2.drawImage(mersable0000Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 1 :
						g2.drawImage(mersable0001Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 2 :
						g2.drawImage(mersable0010Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 3 :
						g2.drawImage(mersable0011Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 4 :
						g2.drawImage(mersable0100Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 5 :
						g2.drawImage(mersable0101Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 6 :
						g2.drawImage(mersable0110Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 7 :
						g2.drawImage(mersable0111Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 8 :
						g2.drawImage(mersable1000Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 9 :
						g2.drawImage(mersable1001Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 10 :
						g2.drawImage(mersable1010Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 11 :
						g2.drawImage(mersable1011Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 12 :
						g2.drawImage(mersable1100Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 13 :
						g2.drawImage(mersable1101Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 14 :
						g2.drawImage(mersable1110Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
					case 15 :
						g2.drawImage(mersable1111Sprite,spriteLength*i,spriteLength*j,spriteLength,spriteLength, frame);
						break;
				}
			}
	//	ImageIO.write(g2, "png", new File("Island.png"));
	}
	
	public static void main(String[] args) {
	new Islandbis();
	}
}
