/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xiangkuang;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import sun.rmi.runtime.Log;

/**
 *
 * @author PC
 */
public class Xiangkuang extends JFrame{
    static BufferedImage image0,image1,image2,image3,image4;
    static Vector<String> vector;
    static int[][] A; 
    static int WIDTH=220;
    static int HEIGHT=220;
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Xiangkuang frame = new Xiangkuang(); 
        frame.setTitle("四叶草");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public Xiangkuang (){
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        add(new JLabel("四叶草"));
        JTextField p0 = new JTextField(24);
        add(p0);
        JButton pb0 = new JButton("...");
        add(pb0);
        pb0.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                p0.setText(chooser.getSelectedFile().getPath());
            }
        });
        add(new JLabel("图1"));
        JTextField p1 = new JTextField(24);
        add(p1);
        JButton pb1 = new JButton("...");
        add(pb1);
        pb1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                p1.setText(chooser.getSelectedFile().getPath());
            }
        });
        add(new JLabel("图2"));
        JTextField p2 = new JTextField(24);
        add(p2);
        JButton pb2 = new JButton("...");
        add(pb2);
        pb2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                p2.setText(chooser.getSelectedFile().getPath());
            }
        });
        add(new JLabel("图3"));
        JTextField p3 = new JTextField(24);
        add(p3);
        JButton pb3 = new JButton("...");
        add(pb3);
        pb3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                p3.setText(chooser.getSelectedFile().getPath());
            }
        });
        add(new JLabel("图4"));
        JTextField p4 = new JTextField(24);
        add(p4);
        JButton pb4 = new JButton("...");
        add(pb4);
        pb4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                p4.setText(chooser.getSelectedFile().getPath());
            }
        });
        add(new JLabel("保存至"));
        JTextField newfile = new JTextField(24);
        add(newfile);
        JButton n = new JButton("...");
        add(n);
        n.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.showOpenDialog(null);
                newfile.setText(chooser.getSelectedFile().getPath());
            }
        });
        add(new JLabel("宽"));
        JTextField width = new JTextField(6);
        add(width);
        add(new JLabel("高"));
        JTextField height= new JTextField(6);
        add(height);
        JButton copy = new JButton("保存");
        add(copy);
        copy.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //                WIDTH = Integer.parseInt("0"+width.getText())==0?500:Integer.parseInt("0"+width.getText());
//                HEIGHT = Integer.parseInt("0"+height.getText())==0?300:Integer.parseInt("0"+height.getText());
                    image0 = change(p0.getText().replaceAll("\\\\", "\\\\"),1,WIDTH,HEIGHT);
                    image1 = change(p1.getText().replaceAll("\\\\", "\\\\"),1,WIDTH,HEIGHT);
                    image2 = change(p2.getText().replaceAll("\\\\", "\\\\"),2,WIDTH,HEIGHT);
                    image3 = change(p3.getText().replaceAll("\\\\", "\\\\"),3,WIDTH,HEIGHT);
                    image4 = change(p4.getText().replaceAll("\\\\", "\\\\"),4,WIDTH,HEIGHT);
                    String p[]=p0.getText().split("\\\\");
                    String path="";
                    path+=p[0];
                    for(int i =1;i<p.length-1;i++){
                        path+="\\"+p[i];
                    }
                    if(newfile.getText().equals(""))newfile.setText(path);
                    get(image0, image1, image2, image3, image4 ,newfile.getText().replaceAll("\\\\", "\\\\"));
                } catch (IOException ex) {
                    Logger.getLogger(Xiangkuang.class.getName()).log(Level.SEVERE, null, ex);
                }
                new MyJDialog(Xiangkuang.this).setVisible(true);
            }
        });
    }
    class MyJDialog extends JDialog{
    //本实例代码可以看到，JDialog窗体和JFrame窗体形式基本相同，甚至在设置窗体的特性
    //时调用的方法名称都基本相同，如设置窗体的大小，设置窗体的关闭状态等
        public MyJDialog(Xiangkuang frame){//定义一个构造方法
        //实例化一个JDialog类对象，指定对话框的父窗体，窗体标题，和类型
            super(frame,"第一个JDialog窗体",true);
            Container container=getContentPane();//创建一个容器
            container.add(new JLabel("保存成功"));//在容器中添加标签
            container.setBackground(Color.green);
            setBounds(120,120,100,100);
            setLocationRelativeTo(null);

    }
}
    

   public static void get(BufferedImage image0,BufferedImage image1,BufferedImage image2,BufferedImage image3,BufferedImage image4,String path) throws IOException{
       int w=500,h=500;
       geta(image0);
       
       BufferedImage image=new BufferedImage(500, 500, BufferedImage.TYPE_4BYTE_ABGR);
       Graphics2D graphics = image.createGraphics();
       graphics.setColor(new Color(247,185,241));
       graphics.fillRect(0, 0, 500, 500);
   //    graphics.drawRect(0, 0, 50, 50);
       graphics.drawImage(image1, 0, 140, WIDTH, HEIGHT, null);
       graphics.rotate(Math.toRadians(90), w / 2,h / 2);
       graphics.drawImage(image2, 0, 140, WIDTH, HEIGHT, null);
       graphics.rotate(Math.toRadians(90), w / 2,h / 2);
       graphics.drawImage(image3, 0, 140, WIDTH, HEIGHT, null);
       graphics.rotate(Math.toRadians(90), w / 2,h / 2);
       graphics.drawImage(image4, 0, 140, WIDTH, HEIGHT, null);
       ImageIO.write(image, "png", new File(path+"\\test.png"));

   }
   
   public static void geta(BufferedImage image0){
       BufferedImage image=new BufferedImage(500, 500, BufferedImage.TYPE_4BYTE_ABGR);
       int rgb;
       A = new int[WIDTH][HEIGHT];
       for ( int i=0;i<WIDTH;i++){
            for ( int j= 0;j<HEIGHT;j++){
                rgb=image0.getRGB(i, j);
                A[i][j]=(rgb & 0xff000000)>>24;
            }
        }
       for ( int i=0;i<WIDTH;i++){
            for ( int j= 0;j<HEIGHT;j++){
                if(A[i][j]==0){
                    image1.setRGB(i, j, image1.getRGB(i, j) & 0xffffff);
                    image2.setRGB(i, j, image2.getRGB(i, j) & 0xffffff);
                    image3.setRGB(i, j, image3.getRGB(i, j) & 0xffffff);
                    image4.setRGB(i, j, image4.getRGB(i, j) & 0xffffff);
                }
            }
        }
   }
   
   public static void get(String path) throws IOException{
        int rgb=0,w,h;
        int[][] r,g,b,n;
        float[][] s1,s2;
        BufferedImage image = ImageIO.read(new File(path));
        BufferedImage prevImage = new BufferedImage(
					image.getWidth(), image.getHeight(),
					BufferedImage.TYPE_4BYTE_ABGR);
        w=prevImage.getWidth();
        h=prevImage.getHeight();
        r = new int[w][h];
        g = new int[w][h];
        b = new int[w][h];
        n = new int[w][h];
        for ( int i=0;i<w;i++){
            for ( int j= 0;j<h;j++){
                rgb=image0.getRGB(i, j);
                rgb=(rgb & 0xff000000)>>24;
                System.out.print(rgb+" ");
                if(rgb>200)
                    prevImage.setRGB(i, j, 1<<24);
                else{
                    prevImage.setRGB(i, j, image.getRGB(i, j));
                }
                    
            }
            System.out.println();
        }
      
       
        
         ImageIO.write(prevImage, "png", new File(path));
    }
    
    public static BufferedImage change(String path,int i,int w,int h) throws IOException{
        i--;
        BufferedImage prevImage = ImageIO.read(new File(path));
        int newWidth = w;
        int newHeight = h;
        BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = image.createGraphics();
        graphics.rotate(Math.toRadians(-90*i), w / 2,h / 2);
        graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);
        return image;
    }
    
    
}



