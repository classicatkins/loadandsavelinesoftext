//https://stackoverflow.com/questions/19871955/java-io-filenotfoundexception-the-system-cannot-find-the-file-specified
//https://www.tabnine.com/code/java/methods/javax.swing.DefaultListModel/%3Cinit%3E
//https://www.tabnine.com/code/java/methods/java.awt.FileDialog/show
//https://docs.oracle.com/javase/7/docs/api/java/awt/FileDialog.html
//https://stackoverflow.com/questions/27680834/how-to-modify-add-of-arraylist
 
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileChooserDemo  
{
    //creates Jlist that will store strings of text
    JList <String> jlist = new JList<>(list);
    static DefaultListModel <String> list = new DefaultListModel<>();

    //creates JFrame
    JFrame frame = new JFrame();

    JMenuItem openItem;
    JMenuItem saveItem; 
    static String fileName;
    String filename;

    //The FileDialog class displays a dialog window from which the user can select a file.
    FileDialog filechooserOpen = new FileDialog(frame, "Choose File", FileDialog.LOAD);
    FileDialog filechooserSave = new FileDialog(frame, "Save File", FileDialog.SAVE);
 
    public FileChooserDemo() 
    {
        //creates menu/menu items/adds thingsto menu bar etc...
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame frame = new JFrame();
        JMenu Menu = new JMenu("Menu");
        JMenuBar menubar = new JMenuBar();
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");

        //adds items to menu and menu to menubar
        Menu.add(openItem);
        Menu.add(saveItem);
        menubar.add(Menu);
 
        //makes everything visable
        frame.setJMenuBar(menubar);
        frame.add(jlist);
        frame.setSize(400, 400);
        frame.setVisible(true); 

        //action listner for open item JMenuItem
        //filechooser opens for file selection
        //grabs file and collects strings of the file
        //displays strings in Jlist
        openItem.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                filechooserOpen.show();   
                if (e.getSource() == saveItem) 
                {
                    fileName = filechooserOpen.getDirectory() + filechooserOpen.getFile();
                } 
                else if (e.getSource() == openItem) 
                {
                    fileName = filechooserOpen.getDirectory() + filechooserOpen.getFile();
                    FileReader();
                }

            }
        });

        //action listner for save item JMenuItem
        //filechooser opens for file selection
        saveItem.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                filechooserSave.show();
                if (filechooserSave.getFile() != null) 
                {
                    fileName = filechooserSave.getDirectory() + filechooserSave.getFile();
                    frame.setTitle(fileName);
                    BufferedWriter bw = null;
                    try 
                    {
                        String mycontent = "";
                        if (jlist.getSelectedIndex() != -1)
                        {
                            for(String item: jlist.getSelectedValuesList())
                            {
                                mycontent += item + "\n";
                            }
                        }
                        // save a file containing the selected items
                        BufferedReader bufferedReader = new BufferedReader(new StringReader(mycontent));
                        while ((mycontent = bufferedReader.readLine()) != null) 
                        {
                            bw.write(mycontent + "\r\n");
                        }
                        bw.close();
                    } 
                    catch (Exception ex) 
                    {
                        System.out.println("File not found");
                    }
                }
            }
        });
    }

//edited FileReaderDemo
//reads txt and converts to line of string
//line of string gets added to the Jlist
public static void FileReader() 
{
    BufferedReader objReader = null;
    StringBuffer stringBuffer = new StringBuffer();
    try 
    {
        objReader = new BufferedReader(new FileReader(fileName));
        String strCurrentLine;
        while ((strCurrentLine = objReader.readLine()) != null)
        {
            list.addElement(strCurrentLine);
            stringBuffer.append(strCurrentLine + "\n");
            System.out.println(strCurrentLine);
        }
        objReader.close();
    } 
    catch (IOException ex) 
    {
        ex.printStackTrace();
    }
}
//main method runs program
    public static void main(String[] args) 
    {
        FileChooserDemo filechooserDemo = new FileChooserDemo();
    }
}