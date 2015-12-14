/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmltest;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javafx.util.converter.CharacterStringConverter;
import javax.smartcardio.CommandAPDU;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.event.TreeModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.omg.CORBA.AnySeqHelper;
import org.w3c.dom.CDATASection;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author zhenhui
 */
public class TreeViewer {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new DomTreeFrame();
                frame.setTitle("TreeViewer");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class DomTreeFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGTH = 400;
    
    private DocumentBuilder builder;
    
    public DomTreeFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGTH);
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        fileMenu.add(openItem);
        
        JMenuItem exItem = new JMenuItem("Exit");
        exItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exItem);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }
    
    /**
     * Open a file and load a document
     */
    public void openFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("dom"));
        
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
         @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
            }

            @Override
            public String getDescription() {
                return "XML files";
            }
        });
        int r = chooser.showOpenDialog(this);
        if (r != JFileChooser.APPROVE_OPTION)
            return;
        final File file = chooser.getSelectedFile();
        
        new SwingWorker<Document, Void>() {
            protected Document doInBackground() throws Exception {
                if (builder == null) {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    builder = factory.newDocumentBuilder();
                }
                return builder.parse(file);
            }
            protected void done() {
                try {
                    Document doc = get();
                    JTree tree = new JTree(new DomTreeModel(doc));
                    tree.setCellRenderer(new DomTreeCellRenderer());
                    
                    setContentPane(new JScrollPane(tree));
                    validate();
                }catch(Exception e) {
                    JOptionPane.showMessageDialog(DomTreeFrame.this, e);
                }
            }
        }.execute();
    }
}

/**
 * 
 * This tree model describe the tree structure of an XML document.
 */
class DomTreeModel implements TreeModel {
    private Document doc;
    public DomTreeModel(Document doc) {
        this.doc = doc;
    }
    public Object getRoot() {
        return doc.getDocumentElement();
    }
    public int getChildCount(Object parent) {
        Node node = (Node)parent;
        NodeList list = node.getChildNodes();
        return list.getLength();
    }
    public Object getChild(Object parent, int index) {
        Node node = (Node)parent;
        NodeList list = node.getChildNodes();
        return list.item(index);
    }
    public int getIndexOfChild(Object parent, Object child) {
        Node node = (Node) parent;
        NodeList list = node.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            if (getChild(node, i) == child) return i;
        }
        return -1;
    }
    public boolean  isLeaf(Object node) {
        return getChildCount(node) == 0;
    }
    public void valueForPathChanged(TreePath path, Object newValue) {
        
    }
    public void addTreeModelListener(TreeModelListener l) {
        
    }
    public void removeTreeModelListener(TreeModelListener l) {
        
    }
    
}

/**
 * 
 * This class renders an XML node.
 */
class DomTreeCellRenderer extends DefaultTreeCellRenderer {
    public Component getTreeCellRendererComponent(JTree tree, Object value, 
        boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Node node = (Node) value;
        if (node instanceof  Element) return elementPanel((Element) node);
        
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        if (node instanceof CharacterData)  setText(characterString((CharacterData) node));
        else setText(node.getClass() + ": " + node.toString());
        return this;
    }
    
    public static JPanel elementPanel(Element e) {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Element: " + e.getTagName()));
        final NamedNodeMap map = e.getAttributes();
        panel.add(new JTable(new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return map.getLength();
            }

            @Override
            public int getColumnCount() {
                return 2;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return columnIndex == 0 ? map.item(rowIndex).getNodeName() : map.item(rowIndex).getNodeValue();
            }
        }));
        return panel;
    }
    
    public static String characterString(CharacterData node) {
        StringBuilder builder = new StringBuilder(node.getData());
 /*
        for (int i = 0; i < builder.length(); i++) {
            if (builder.charAt(i) == '\r') {
                builder.replace(i, i+1, "\\r");
                i++;
            }
            else if (builder.charAt(i) == '\n') {
                builder.replace(i, i+1, "\\n");
                i++;
            }
            else if (builder.charAt(i) == '\t') {
                builder.replace(i, i+1, "\\t");
                i++;
            }
        }
*/
        if (node instanceof CDATASection)
            builder.insert(0, "CDATASelection: ");
        else if (node instanceof Text)
            builder.insert(0, "Text: ");
        else if (node instanceof  Comment) builder.insert(0, "Comment: ");
        else {}
        return builder.toString();
    }
}