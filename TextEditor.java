package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TextEditor extends JFrame implements ActionListener {
    JButton LoadButton;
    JButton SaveButton;
    JButton StartSearchButton;
    JButton previousMatchButton;
    JButton nextMatchButton;
    JCheckBox useRegExCheckbox;
    JScrollPane ScrollPane;
    JTextField SearchField;
    public JTextArea TextArea;
    JMenuItem MenuLoad;
    JMenuItem MenuSave;
    JMenuItem MenuExit;
    JMenuBar Filemenu;
    JMenu MenuFile;
    JMenu menuSearch;
    JMenuItem menuStartSearch;
    JMenuItem menuNextMatch;
    JMenuItem menuPreviousMatch;
    JMenuItem menuUseRegExp;
    JFileChooser fileChooser;
    String fileStr = "";
    int start_Intx = 0;
    int intx_Diff = 0;
    /*String textToSave2 = "          Sonnet I\n" +
            "\n" +
            "\n" +
            "FROM fairest creatures we desire increase,\n" +
            "That thereby beauty's rose might never die,\n" +
            "But as the riper should by time decease,\n" +
            "His tender heir might bear his memory:\n" +
            "But thou, contracted to thine own bright eyes,\n" +
            "Feed'st thy light'st flame with self-substantial fuel,\n" +
            "Making a famine where abundance lies,\n" +
            "Thyself thy foe, to thy sweet self too cruel.\n" +
            "Thou that art now the world's fresh ornament\n" +
            "And only herald to the gaudy spring,\n" +
            "Within thine own bud buriest thy content\n" +
            "And, tender churl, makest waste in niggarding.\n" +
            "Pity the world, or else this glutton be,\n" +
            "To eat the world's due, by the grave and thee.\n" +
            "\n" +
            " Sonnet II\n" +
            "       \n" +
            "         \n" +
            "When forty winters shall beseige thy brow,\n" +
            "And dig deep trenches in thy beauty's field,\n" +
            "Thy youth's proud livery, so gazed on now,\n" +
            "Will be a tatter'd weed, of small worth held:\n" +
            "Then being ask'd where all thy beauty lies,\n" +
            "Where all the treasure of thy lusty days,\n" +
            "To say, within thine own deep-sunken eyes,\n" +
            "Were an all-eating shame and thriftless praise.\n" +
            "How much more praise deserved thy beauty's use,\n" +
            "If thou couldst answer 'This fair child of mine\n" +
            "Shall sum my count and make my old excuse,'\n" +
            "Proving his beauty by succession thine!\n" +
            "This were to be new made when thou art old,\n" +
            "And see thy blood warm when thou feel'st it cold.\n" +
            "\n" +
            "Sonnet III\n" +
            "\n" +
            "\n" +
            "Look in thy glass, and tell the face thou viewest\n" +
            "Now is the time that face should form another;\n" +
            "Whose fresh repair if now thou not renewest,\n" +
            "Thou dost beguile the world, unbless some mother.\n" +
            "For where is she so fair whose unear'd womb\n" +
            "Disdains the tillage of thy husbandry?\n" +
            "Or who is he so fond will be the tomb\n" +
            "Of his self-love, to stop posterity?\n" +
            "Thou art thy mother's glass, and she in thee\n" +
            "Calls back the lovely April of her prime:\n" +
            "So thou through windows of thine age shall see\n" +
            "Despite of wrinkles this thy golden time.\n" +
            "But if thou live, remember'd not to be,\n" +
            "Die single, and thine image dies with thee.\n" +
            "\n" +
            "Sonnet IV\n" +
            "\n" +
            "\n" +
            "Unthrifty loveliness, why dost thou spend\n" +
            "Upon thyself thy beauty's legacy?\n" +
            "Nature's bequest gives nothing but doth lend,\n" +
            "And being frank she lends to those are free.\n" +
            "Then, beauteous niggard, why dost thou abuse\n" +
            "The bounteous largess given thee to give?\n" +
            "Profitless usurer, why dost thou use\n" +
            "So great a sum of sums, yet canst not live?\n" +
            "For having traffic with thyself alone,\n" +
            "Thou of thyself thy sweet self dost deceive.\n" +
            "Then how, when nature calls thee to be gone,\n" +
            "What acceptable audit canst thou leave?\n" +
            "Thy unused beauty must be tomb'd with thee,\n" +
            "Which, used, lives th' executor to be.";
    String textToSearch = "Sonnet";*/
    public TextEditor() {
        setTitle("Text Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        fileChooser = new JFileChooser();
        fileChooser.setName("FileChooser");
        Filemenu = new JMenuBar();
        MenuFile = new JMenu("File");
        MenuFile.setName("MenuFile");
        Filemenu.add(MenuFile);
        menuSearch = new JMenu("Search");
        menuSearch.setName("MenuSearch");
        Filemenu.add(menuSearch);
        MenuLoad = new JMenuItem("Load");
        MenuSave = new JMenuItem("Save");
        MenuExit = new JMenuItem("Exit");
        MenuLoad.setName("MenuOpen");
        MenuSave.setName("MenuSave");
        MenuExit.setName("MenuExit");
        MenuLoad.addActionListener(this);
        MenuSave.addActionListener(this);
        MenuExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        menuStartSearch = new JMenuItem("Start Search");
        menuStartSearch.setName("MenuStartSearch");
        menuStartSearch.addActionListener(this);
        menuNextMatch = new JMenuItem("Next Match");
        menuNextMatch.setName("MenuNextMatch");
        menuNextMatch.addActionListener(this);
        menuPreviousMatch = new JMenuItem("Previous Match");
        menuPreviousMatch.setName("MenuPreviousMatch");
        menuPreviousMatch.addActionListener(this);
        menuUseRegExp = new JMenuItem("Use Regular Expressions");
        menuUseRegExp.setName("MenuUseRegExp");
        MenuFile.add(MenuLoad);
        MenuFile.add(MenuSave);
        MenuFile.add(MenuExit);
        menuSearch.add(menuStartSearch);
        menuSearch.add(menuPreviousMatch);
        menuSearch.add(menuNextMatch);
        menuSearch.add(menuUseRegExp);
        menuUseRegExp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(useRegExCheckbox.isSelected()) {
                    useRegExCheckbox.setSelected(false);
                } else {
                    useRegExCheckbox.setSelected(true);
                }
            }
        });
        setJMenuBar(Filemenu);
        Filemenu.getParent().getParent().getParent().add(fileChooser);
        fileChooser.setVisible(false);
        JPanel topLevel = new JPanel();
        FlowLayout topLayout = new FlowLayout();
        topLevel.setLayout(topLayout);
        topLayout.setAlignment(FlowLayout.TRAILING);
        JPanel downLevel = new JPanel(topLayout);
        SearchField = new JTextField(50);
        SearchField.setName("SearchField");
        try{
            LoadButton = new JButton(new ImageIcon("C:\\learning\\Java\\icons\\icon_load.png"));
            LoadButton.setName("OpenButton");
            LoadButton.setToolTipText("Load File");
            LoadButton.addActionListener(this);
            topLevel.add(LoadButton);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        try {
            Icon ic = new ImageIcon("C:\\learning\\Java\\icons\\ic_save.png");
            SaveButton = new JButton(ic);
            SaveButton.setName("SaveButton");
            SaveButton.setToolTipText("Save");
            SaveButton.addActionListener(this);
            topLevel.add(SaveButton);
        }
        catch(Exception e) {
            System.out.println(e);
        }
        topLevel.add(SearchField);
        StartSearchButton = new JButton(new ImageIcon("C:\\learning\\Java\\icons\\ic_search.png"));
        StartSearchButton.setName("StartSearchButton");
        StartSearchButton.addActionListener(this);
        topLevel.add(StartSearchButton);
        previousMatchButton = new JButton(new ImageIcon("C:\\learning\\Java\\icons\\ic_prev_sear.png"));
        previousMatchButton.setName("PreviousMatchButton");
        previousMatchButton.addActionListener(this);
        topLevel.add(previousMatchButton);

        nextMatchButton = new JButton(new ImageIcon("C:\\learning\\Java\\icons\\ic_next_sear.png"));
        nextMatchButton.setName("NextMatchButton");
        nextMatchButton.addActionListener(this);
        topLevel.add(nextMatchButton);
        useRegExCheckbox = new JCheckBox("Use Regex");
        useRegExCheckbox.setName("UseRegExCheckbox");
        topLevel.add(useRegExCheckbox);

        TextArea = new JTextArea(30, 90);
        TextArea.setName("TextArea");
        ScrollPane = new JScrollPane(TextArea);
        ScrollPane.setName("ScrollPane");
        ScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        downLevel.add(ScrollPane, BorderLayout.WEST);
        topLevel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        downLevel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        add(topLevel, BorderLayout.PAGE_START);
        add(downLevel, BorderLayout.LINE_START);
        pack();
        setVisible(true);
        /*TextArea.setText(textToSave2);
        SearchField.setText(textToSearch);*/
    }

    public static void main(String[] args) {
        new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int retVal;
        if(ae.getSource() == this.SaveButton || ae.getSource() == this.MenuSave) {
            fileChooser.setVisible(true);
            retVal = fileChooser.showSaveDialog(null);
            if(retVal == JFileChooser.APPROVE_OPTION) {
                fileStr = String.valueOf(fileChooser.getSelectedFile());
                try (FileWriter fw = new FileWriter(fileStr)) {
                    fw.write(TextArea.getText());
                } catch (Exception e) {
                    SearchField.setText("File writing error!");
                }
            }
        }
        else if(ae.getSource() == this.LoadButton || ae.getSource() == this.MenuLoad) {
            try{
                TextArea.setText("");
                fileChooser.setVisible(true);
                retVal = fileChooser.showOpenDialog(null);
                if(retVal == JFileChooser.APPROVE_OPTION) {
                    fileStr = String.valueOf(fileChooser.getSelectedFile());
                    TextArea.setText(new String(Files.readAllBytes(Paths.get(fileStr))));
                }
            }
            catch(Exception e) {
                TextArea.setText("");
            }
        } else if(ae.getSource() == this.previousMatchButton || ae.getSource() == this.menuPreviousMatch) {
            Runnable prv = new Runnable() {
                @Override
                public void run() {
                    JFrame pop = new JFrame();
                    int end_intx = 0;
                    String text = TextArea.getText();
                    String sea_String = SearchField.getText();
                    Pattern pattern = Pattern.compile(SearchField.getText());
                    Matcher matcher = pattern.matcher(TextArea.getText().substring(intx_Diff));
                    try{
                        if("prev".equals(Thread.currentThread().getName())){
                            if(useRegExCheckbox.isSelected()) {
                                Matcher matcher1 = pattern.matcher(TextArea.getText().substring(0,intx_Diff));
                                if(matcher1.find()) {
                                    matcher1.reset();
                                    while(matcher1.find()) {
                                        start_Intx = matcher1.start();
                                        end_intx = matcher1.end();
                                    }
                                    TextArea.setCaretPosition(end_intx);
                                    TextArea.select(start_Intx, end_intx);
                                    TextArea.grabFocus();
                                    intx_Diff = start_Intx;
                                    if (intx_Diff <= 0) {
                                        intx_Diff = text.length();
                                    }
                                    //JOptionPane.showMessageDialog(pop, intx_Diff + "first");
                                } else {
                                    intx_Diff = text.length();
                                    start_Intx = 0;
                                    matcher1.reset();
                                    matcher1 = pattern.matcher(TextArea.getText().substring(0,intx_Diff));
                                    //JOptionPane.showMessageDialog(pop, intx_Diff);
                                    while(matcher1.find()) {
                                        start_Intx = matcher1.start();
                                        end_intx = matcher1.end();
                                    }
                                    TextArea.setCaretPosition(end_intx);
                                    TextArea.select(start_Intx, end_intx);
                                    TextArea.grabFocus();
                                    intx_Diff = start_Intx;
                                }

                            } else {
                                start_Intx = text.lastIndexOf(sea_String, start_Intx - 1);
                                if(start_Intx > 0) {
                                    TextArea.setCaretPosition(start_Intx + sea_String.length());
                                    TextArea.select(start_Intx, start_Intx + sea_String.length());
                                    TextArea.grabFocus();
                                } else {
                                    start_Intx = text.length();
                                    start_Intx = text.lastIndexOf(sea_String, start_Intx - 1);
                                    if(start_Intx > 0) {
                                        TextArea.setCaretPosition(start_Intx + sea_String.length());
                                        TextArea.select(start_Intx, start_Intx + sea_String.length());
                                        TextArea.grabFocus();
                                    } else {
                                        start_Intx = text.length();
                                    }
                                }
                            }
                        }

                    } catch(Exception e) {
                        return;
                    }
                }
            };
            Thread t = new Thread(prv);
            t.setName("prev");
            t.start();

        } else if(ae.getSource() == this.nextMatchButton || ae.getSource() == this.menuNextMatch || ae.getSource() == this.menuStartSearch || ae.getSource() == this.StartSearchButton) {
            if(ae.getSource() == this.menuStartSearch || ae.getSource() == this.StartSearchButton) {
                intx_Diff = 0;
                start_Intx = 0;
            }
            Runnable nxt = new Runnable() {
                @Override
                public void run() {
                    String text = TextArea.getText();
                    Pattern pattern = Pattern.compile(SearchField.getText());
                    Matcher matcher = pattern.matcher(TextArea.getText().substring(intx_Diff));
                    String sea_String = SearchField.getText();
                    try{
                        if("next".equals(Thread.currentThread().getName())){
                            if(useRegExCheckbox.isSelected()) {
                                if(matcher.find()) {
                                    start_Intx = matcher.start();
                                    TextArea.setCaretPosition(matcher.end() + intx_Diff);
                                    TextArea.select(start_Intx + intx_Diff, matcher.end() + intx_Diff);
                                    TextArea.grabFocus();
                                    intx_Diff = intx_Diff + start_Intx + 1;
                                } else {
                                    intx_Diff = 0;
                                    start_Intx = 0;
                                    matcher.reset();
                                    matcher = pattern.matcher(TextArea.getText().substring(intx_Diff));
                                    if(matcher.find()) {
                                        start_Intx = matcher.start();
                                        TextArea.setCaretPosition(matcher.end() + intx_Diff);
                                        TextArea.select(start_Intx + intx_Diff, matcher.end() + intx_Diff);
                                        TextArea.grabFocus();
                                        intx_Diff = intx_Diff + start_Intx + 1;
                                    }
                                }
                            } else {
                                start_Intx = text.indexOf(sea_String, start_Intx + 1);
                                if(start_Intx > 0) {
                                    TextArea.setCaretPosition(start_Intx + sea_String.length());
                                    TextArea.select(start_Intx, start_Intx + sea_String.length());
                                    TextArea.grabFocus();
                                } else {
                                    start_Intx = 0;
                                    start_Intx = text.indexOf(sea_String, start_Intx + 1);
                                    if(start_Intx > 0) {
                                        TextArea.setCaretPosition(start_Intx + sea_String.length());
                                        TextArea.select(start_Intx, start_Intx + sea_String.length());
                                        TextArea.grabFocus();
                                    } else {
                                        start_Intx = 0;
                                    }
                                }
                            }
                        }

                    } catch(Exception e) {
                        return;
                    }
                }
            };
            Thread t = new Thread(nxt);
            t.setName("next");
            t.start();
        }
    }
}
