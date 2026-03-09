package kr.ac.green;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ex2 extends JFrame implements ActionListener{
	private Counter counter;
	
	private JButton btnSave;
	private JButton btnLoad;
	private JButton btnNew;
	
	public Ex2() {
		btnSave = new JButton("save");
		btnLoad = new JButton("Load");
		btnNew = new JButton("New");
		
		setLayout(new GridLayout(1,0));
		add(btnNew);
		add(btnSave);
		add(btnLoad);
		
		btnNew.addActionListener(this);
		btnSave.addActionListener(this);
		btnLoad.addActionListener(this);
		
		setTitle("Object I/O");
		setLocation(100,100);
		setSize(300,100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object src = ae.getSource();
		
		if(src == btnNew) {
			counter = new Counter();
		}else if(src == btnSave) {
			if(counter !=null) {
				save();
			}else {
				JOptionPane.showMessageDialog(this, "new 한 후 시도하세요");
			}
		}else {
			
		}
	}
	private void load() {
		try(
				FileInputStream fis = new FileInputStream("counter.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				
		){
			counter = (Counter)ois.readObject();
			counter.setVisible(true);
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	private void save() {
		try(
				FileOutputStream fos = new FileOutputStream("counter.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);
		){
			oos.writeObject(counter);
			oos.flush();
			oos.reset();
			
			counter.dispose();
			counter = null;
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Ex2();
		
	}
}
