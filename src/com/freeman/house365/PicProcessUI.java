package com.freeman.house365;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PicProcessUI extends JDialog implements MouseMotionListener,
		ActionListener {

	private final JPanel contentPanel = new JPanel();
	int bottom_y;
	final static int correction = 52;
	BufferedImage img = null;
	String pathname;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			PicProcessUI dialog = new PicProcessUI(
					"http://img17.house365.com/njhouse/2014/07/13/140521508453c1e16c9618c.jpg");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public PicProcessUI(String strURL, String pathname) {

		this.pathname = pathname;
		this.addMouseMotionListener(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel pictureLabel = new JLabel();

			URL picUrl;

			try {
				picUrl = new URL(strURL);

				URLConnection uc = picUrl.openConnection();
				InputStream is = uc.getInputStream();
				img = ImageIO.read(is);
			}  catch (IOException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, 
						"网络错误，无法下载房源图片."
				);
				e.printStackTrace();
			}
			ImageIcon icon = new ImageIcon(img);
			pictureLabel.setIcon(icon);
			contentPanel.add(pictureLabel);
			contentPanel.setSize(img.getWidth(), img.getHeight());
			bottom_y = img.getHeight() + correction;
		}
		setBounds(100, 100, contentPanel.getWidth() + 50,
				contentPanel.getHeight() + 100);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("剪裁并保存图片");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("放弃图片");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);

			}
		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.green);
		g2d.fillRect(0, bottom_y, 3000, 2);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		bottom_y = e.getY();
		repaint();
		System.out.println(bottom_y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			int w = img.getWidth();
			int h = bottom_y -correction;
			BufferedImage bi = new BufferedImage(w, h,
					BufferedImage.TYPE_3BYTE_BGR);
			Graphics g = bi.getGraphics();
			g.drawImage(img, 0, 0, null);
			ImageIO.write(bi, "jpg", new File(pathname));
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, 
					"裁剪保存图片出错."
			);
		}
		dispose();
	}

}
