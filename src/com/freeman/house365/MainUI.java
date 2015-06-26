package com.freeman.house365;

import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Iterator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainUI implements ActionListener {

	String picRootPath = ".//House365图片//";
	// String picRootPath = "//home//freeman//House365图片//";
	String txDescText;
	private JFrame frame;
	private JTextField txCommunity;
	private JTextField txTitle;
	private JTextField txPrice;
	private JLabel label_4;
	private JTextField txPriceCondition;
	private JTextField txPriceDesc;
	private JLabel label_5;
	private JLabel label_6;
	private JTextField txHouseType;
	private JLabel label_7;
	private JTextField txArea;
	private JLabel label_8;
	private JTextField txFloor;
	private JLabel label_9;
	private JTextField txOwnerShip;
	private JLabel label_11;
	private JTextField txFurnishment;
	private JLabel label_12;
	private JTextField txBuildDate;
	private JLabel label_13;
	private JTextField txFace;
	private JButton btnCpDesc;
	private JButton btnAnalyse;
	private JTextField txURL;
	private JCheckBox picSwitch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setBounds(0, 0, 282, 539);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label_2 = new JLabel("小区名:");
		label_2.setFont(new Font("Dialog", Font.BOLD, 20));
		label_2.setBounds(18, 12, 100, 25);
		frame.getContentPane().add(label_2);
		txCommunity = new JTextField();
		txCommunity.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Clipboard clip = Toolkit.getDefaultToolkit()
						.getSystemClipboard();
				Transferable tText = new StringSelection(txCommunity.getText());
				clip.setContents(tText, null);
			}
		});
		txCommunity.setFont(new Font("Dialog", Font.PLAIN, 20));
		txCommunity.setBounds(118, 12, 150, 25);
		frame.getContentPane().add(txCommunity);
		txCommunity.setColumns(10);

		JLabel label_3 = new JLabel("标题:");
		label_3.setFont(new Font("Dialog", Font.BOLD, 20));
		label_3.setBounds(18, 42, 100, 25);
		frame.getContentPane().add(label_3);
		txTitle = new JTextField();
		txTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Clipboard clip = Toolkit.getDefaultToolkit()
						.getSystemClipboard();
				Transferable tText = new StringSelection(txTitle.getText());
				clip.setContents(tText, null);
			}
		});
		txTitle.setColumns(10);
		txTitle.setBounds(118, 42, 150, 25);
		frame.getContentPane().add(txTitle);

		JLabel label = new JLabel("售价:");
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setBounds(18, 70, 100, 25);
		frame.getContentPane().add(label);
		txPrice = new JTextField();
		txPrice.setFont(new Font("Dialog", Font.PLAIN, 20));
		txPrice.setColumns(10);
		txPrice.setBounds(118, 70, 150, 25);
		frame.getContentPane().add(txPrice);

		label_4 = new JLabel("价格条件:");
		label_4.setFont(new Font("Dialog", Font.BOLD, 20));
		label_4.setBounds(18, 100, 100, 25);
		frame.getContentPane().add(label_4);
		txPriceCondition = new JTextField();
		txPriceCondition.setFont(new Font("Dialog", Font.PLAIN, 20));
		txPriceCondition.setColumns(10);
		txPriceCondition.setBounds(118, 100, 150, 25);
		frame.getContentPane().add(txPriceCondition);

		label_5 = new JLabel("价格描述:");
		label_5.setFont(new Font("Dialog", Font.BOLD, 20));
		label_5.setBounds(18, 130, 100, 24);
		frame.getContentPane().add(label_5);
		txPriceDesc = new JTextField();
		txPriceDesc.setFont(new Font("Dialog", Font.PLAIN, 20));
		txPriceDesc.setColumns(10);
		txPriceDesc.setBounds(118, 130, 150, 25);
		frame.getContentPane().add(txPriceDesc);

		label_6 = new JLabel("户型:");
		label_6.setFont(new Font("Dialog", Font.BOLD, 20));
		label_6.setBounds(18, 160, 100, 25);
		frame.getContentPane().add(label_6);
		txHouseType = new JTextField();
		txHouseType.setFont(new Font("Dialog", Font.PLAIN, 20));
		txHouseType.setColumns(10);
		txHouseType.setBounds(118, 160, 150, 25);
		frame.getContentPane().add(txHouseType);

		label_7 = new JLabel("产证面积:");
		label_7.setFont(new Font("Dialog", Font.BOLD, 20));
		label_7.setBounds(18, 190, 100, 25);
		frame.getContentPane().add(label_7);
		txArea = new JTextField();
		txArea.setFont(new Font("Dialog", Font.PLAIN, 20));
		txArea.setColumns(10);
		txArea.setBounds(118, 190, 150, 25);
		frame.getContentPane().add(txArea);

		label_8 = new JLabel("楼层:");
		label_8.setFont(new Font("Dialog", Font.BOLD, 20));
		label_8.setBounds(18, 220, 100, 25);
		frame.getContentPane().add(label_8);
		txFloor = new JTextField();
		txFloor.setFont(new Font("Dialog", Font.PLAIN, 20));
		txFloor.setColumns(10);
		txFloor.setBounds(118, 220, 150, 25);
		frame.getContentPane().add(txFloor);

		label_9 = new JLabel("权属:");
		label_9.setFont(new Font("Dialog", Font.BOLD, 20));
		label_9.setBounds(18, 250, 100, 25);
		frame.getContentPane().add(label_9);
		txOwnerShip = new JTextField();
		txOwnerShip.setFont(new Font("Dialog", Font.PLAIN, 20));
		txOwnerShip.setColumns(10);
		txOwnerShip.setBounds(118, 250, 150, 25);
		frame.getContentPane().add(txOwnerShip);

		label_11 = new JLabel("装修程度:");
		label_11.setFont(new Font("Dialog", Font.BOLD, 20));
		label_11.setBounds(18, 280, 100, 25);
		frame.getContentPane().add(label_11);
		txFurnishment = new JTextField();
		txFurnishment.setFont(new Font("Dialog", Font.PLAIN, 20));
		txFurnishment.setColumns(10);
		txFurnishment.setBounds(118, 280, 150, 25);
		frame.getContentPane().add(txFurnishment);

		label_12 = new JLabel("建筑年代:");
		label_12.setFont(new Font("Dialog", Font.BOLD, 20));
		label_12.setBounds(18, 310, 100, 25);
		frame.getContentPane().add(label_12);
		txBuildDate = new JTextField();
		txBuildDate.setFont(new Font("Dialog", Font.PLAIN, 20));
		txBuildDate.setColumns(10);
		txBuildDate.setBounds(118, 310, 150, 25);
		frame.getContentPane().add(txBuildDate);

		label_13 = new JLabel("朝向:");
		label_13.setFont(new Font("Dialog", Font.BOLD, 20));
		label_13.setBounds(18, 340, 100, 25);
		frame.getContentPane().add(label_13);
		txFace = new JTextField();
		txFace.setFont(new Font("Dialog", Font.PLAIN, 20));
		txFace.setColumns(10);
		txFace.setBounds(118, 340, 150, 25);
		frame.getContentPane().add(txFace);

		btnCpDesc = new JButton("点击复制房源描述");
		btnCpDesc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clipboard clip = Toolkit.getDefaultToolkit()
						.getSystemClipboard();
				Transferable tText = new StringSelection(txDescText);
				clip.setContents(tText, null);
			}
		});
		btnCpDesc.setFont(new Font("Dialog", Font.BOLD, 20));
		btnCpDesc.setBounds(15, 370, 252, 25);
		frame.getContentPane().add(btnCpDesc);
		btnCpDesc.setBounds(15, 400, 252, 25);

		txURL = new JTextField();
		txURL.setBounds(15, 435, 252, 25);
		frame.getContentPane().add(txURL);
		txURL.setColumns(10);
		btnAnalyse = new JButton("分析");
		btnAnalyse.addActionListener(this);
		btnAnalyse.setFont(new Font("Dialog", Font.BOLD, 20));
		btnAnalyse.setBounds(15, 465, 252, 25);
		frame.getContentPane().add(btnAnalyse);

		picSwitch = new JCheckBox("Pic");
		picSwitch.setBounds(18, 369, 126, 23);
		frame.getContentPane().add(picSwitch);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Parser parser = new Parser();
		parser.connect(txURL.getText());
		parser.parseType();
		if (!(parser.getType().equals("住宅"))) {
			JOptionPane.showMessageDialog(null, "请注意该房源不是普通住宅");
		}
		parser.parseCommunity();
		txCommunity.setText(parser.getCommunity());
		parser.parseTitle();
		txTitle.setText(parser.getTitle());
		parser.parsePrice();
		txPrice.setText(parser.getPrice());
		parser.parsePriceCondition();
		txPriceCondition.setText(parser.getPriceCondition());
		parser.parsePriceDesc();
		txPriceDesc.setText(parser.getPriceDesc());
		parser.parseHouseType();
		txHouseType.setText(parser.getHouseType());
		parser.parseArea();
		txArea.setText(parser.getArea());
		parser.parseFloor();
		txFloor.setText(parser.getFloor());
		parser.parseOwnerShip();
		txOwnerShip.setText(parser.getOwnerShip());
		parser.parseFurnishment();
		txFurnishment.setText(parser.getFurnishment());
		parser.parseBuildDate();
		txBuildDate.setText(parser.getBuildDate());
		parser.parseFace();
		txFace.setText(parser.getFace());
		parser.parseDescText();
		txDescText = parser.getDescText();
		if (picSwitch.isSelected()) {
			parser.parsePictureUrl();

			Iterator it = parser.getPictureUrl().iterator();
			Dialog dialog;
			File rootDir = new File(picRootPath);
			if (!rootDir.exists()) {
				rootDir.mkdirs();
			}
			File subDir = new File(picRootPath + parser.getTitle());
			subDir.mkdir();
			int count = 0;
			while (it.hasNext()) {
				dialog = new PicProcessUI((String) it.next(), picRootPath
						+ parser.getTitle() + "//" + count + ".jpg");
				count++;
				((JDialog) dialog)
						.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		}
		txURL.setText("");
	}
}
