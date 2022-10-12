import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Notepad extends JFrame  {
    boolean editable=false;
    JFrame jf;
    String filename;
    Container contents;
    JMenu menu;
    JMenuBar menubar;
    JMenuItem openMI;
    JMenuItem undoMI;
    JMenuItem saveMI;
    JMenuItem closeMI;
    JToolBar toolbar;
    JCheckBox readOnly;
    JButton openB;
    JButton rollBackB;
    JButton saveB;
    JButton closeB;
    JTextArea textArea;

Notepad()
    {
        jf=new JFrame();
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.setSize(600,600);

        contents=jf.getContentPane();

        textArea = new JTextArea();
        textArea.setEnabled(editable);
        JScrollPane scrollPane = new JScrollPane(textArea);
        contents.add(scrollPane, BorderLayout.CENTER);
        //Menu
        menu= new JMenu("File");
        menubar=new JMenuBar();

        openMI= new JMenuItem("Open");
        undoMI= new JMenuItem("Undo");
        saveMI= new JMenuItem("Save");
        closeMI= new JMenuItem("Close");
        menu.add(openMI);
        menu.add(undoMI);
        menu.add(saveMI);
        menu.add(closeMI);
        menubar.add(menu);
        contents.add(menubar, BorderLayout.NORTH);
            //Action listeners:
            openMI.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    open();
                }
            });
            undoMI.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        undo();
                }
            });
            saveMI.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        save();
                }
            });
            closeMI.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    close();
                }
            });
        //tollbar:
        toolbar=new JToolBar("Toolbar");
        contents.add(toolbar, BorderLayout.SOUTH);
        readOnly = new JCheckBox("Read only", true);
        readOnly.setEnabled(true);
        openB = new JButton("Open");
        rollBackB = new JButton("RollBack");
        saveB = new JButton("Save");
        closeB = new JButton("Close");
        toolbar.add(readOnly);
        toolbar.add(openB);
        toolbar.add(rollBackB);
        toolbar.add(saveB);
        toolbar.add(closeB);
        //Action listeners
        readOnly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editable=!editable;
                textArea.setEnabled(editable);
            }
        });
        openB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open();
            }
        });
        rollBackB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    undo();
            }
        });
        saveB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    save();
            }
        });
        closeB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {close();}
        });

        jf.setVisible(true);
    }

    void close(){dispose();System.exit(0);}
    void undo()
    {
        try {
            this.textArea.setText("");
            Scanner scan = new Scanner(new FileReader(filename));
            while (scan.hasNext())
                this.textArea.append(scan.nextLine() + "\n");
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }

    }
    void save()
    {
        JFileChooser save = new JFileChooser();
        int option = save.showSaveDialog(this);
         if (option == JFileChooser.APPROVE_OPTION) {
            try {
                filename=save.getSelectedFile().getPath();
                BufferedWriter out = new BufferedWriter(new FileWriter(filename));
                out.write(this.textArea.getText());
                out.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    void  open()
    {
        JFileChooser open = new JFileChooser();
        int option = open.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
           textArea.setText("");
            try {
                filename=open.getSelectedFile().getPath();
                Scanner scan = new Scanner(new FileReader(filename));
                while (scan.hasNext())
                    this.textArea.append(scan.nextLine() + "\n");
            } catch (Exception ex) {

                System.out.println(ex.getMessage());
            }
        }
    }
}
