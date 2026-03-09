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

public class Ex3 extends JFrame implements ActionListener {
	
	private Counter2 counter;
	
	private JButton btnSave;
	private JButton btnLoad;
	private JButton btnNew;
	
	public Ex3() {
		btnSave = new JButton("Save");
		btnLoad = new JButton("Load");
		btnNew = new JButton("New");
		
		setLayout(new GridLayout(1, 0));
		add(btnNew);
		add(btnSave);
		add(btnLoad);
		
		btnNew.addActionListener(this);
		btnSave.addActionListener(this);
		btnLoad.addActionListener(this);
		
		setTitle("Object I/O");
		setLocation(100, 100);
		setSize(300, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object src = ae.getSource();
		
		if(src == btnNew) {
			counter = new Counter2();
		} else if(src == btnSave) {
			if(counter != null) {
				save();
			} else {
				JOptionPane.showMessageDialog(this, "new 한 후 시도하세요");
			}
		} else {
			load();
		}
	}
	
	private void load() {
		try(
			FileInputStream fis = new FileInputStream("counter.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
		) {
			CounterInfo info = (CounterInfo)ois.readObject();
			counter = new Counter2(info);
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void save() {
		try(				
			FileOutputStream fos = new FileOutputStream("counter.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
		) {
			CounterInfo info = counter.getInfo();
			oos.writeObject(info);
			oos.flush();
			oos.reset();
			
			counter.dispose();
			counter = null;
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		new Ex3();
	}
}