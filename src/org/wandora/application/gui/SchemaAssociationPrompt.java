/*
 * WANDORA
 * Knowledge Extraction, Management, and Publishing Application
 * http://wandora.org
 * 
 * Copyright (C) 2004-2015 Wandora Team
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * 
 * SchemaAssociationPrompt.java
 *
 * Created on August 11, 2004, 9:16 AM
 */

package org.wandora.application.gui;


import org.wandora.piccolo.WandoraManager;
import org.wandora.topicmap.SchemaBox;
import org.wandora.topicmap.TMBox;
import org.wandora.application.*;
import org.wandora.topicmap.*;
import org.wandora.*;
import java.util.*;
import java.awt.*;
import org.wandora.application.gui.*;
import org.wandora.application.gui.simple.*;
import org.wandora.topicmap.layered.*;

/**
 * @deprecated 
 *
 * @author  olli, ak
 */
public class SchemaAssociationPrompt extends javax.swing.JDialog implements Runnable {

    private Topic topic;
    private Wandora parent;
    private boolean running;
    
    private Object newAssociationType;
    private Object newRole;
    
    private HashMap roleMap;
    
    private Thread workerThread;
    
    private Topic associationType;
    private Topic associationTypeBeforeAdd;
    
    private Topic topicRole;
    private Topic topicRoleBeforeAdd;
    
    private boolean cancelled;
    
    private Association original;
    
    /** Creates new form SchemaAssociationPrompt */
    public SchemaAssociationPrompt(Wandora parent, Topic topic,boolean modal)  throws TopicMapException {
        this(parent,topic,modal,null);
    }
    public SchemaAssociationPrompt(Wandora parent, Topic topic,boolean modal,Association original)  throws TopicMapException {
        super(parent, modal);
        this.original=original;
        this.parent=parent;
        this.topic=topic;
        roleMap=new HashMap();
        initComponents();
        topicLabel.setText(topic.getBaseName());
        
        workerThread=new Thread(this);
        running=true;
        workerThread.start();
        
        parent.centerWindow(this);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        cancelButton = new org.wandora.application.gui.simple.SimpleButton();
        jPanel4 = new javax.swing.JPanel();
        applyButton = new org.wandora.application.gui.simple.SimpleButton();
        okButton = new org.wandora.application.gui.simple.SimpleButton();
        associationPanel = new javax.swing.JPanel();
        associationTypePanel = new javax.swing.JPanel();
        associationTypeComboBox = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        thisPanel = new javax.swing.JPanel();
        roleComboBox = new javax.swing.JComboBox();
        topicLabel = new javax.swing.JLabel();
        otherPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Association editor");
        getContentPane().setLayout(new java.awt.BorderLayout(15, 5));

        buttonPanel.setPreferredSize(new java.awt.Dimension(300, 45));
        buttonPanel.setLayout(new java.awt.BorderLayout());

        jPanel5.setPreferredSize(new java.awt.Dimension(105, 45));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 10));

        cancelButton.setText("Cancel");
        cancelButton.setMinimumSize(new java.awt.Dimension(75, 23));
        cancelButton.setPreferredSize(new java.awt.Dimension(75, 23));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        jPanel5.add(cancelButton);

        buttonPanel.add(jPanel5, java.awt.BorderLayout.WEST);

        jPanel4.setPreferredSize(new java.awt.Dimension(185, 45));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 10));

        applyButton.setText("Add");
        applyButton.setEnabled(false);
        applyButton.setMaximumSize(new java.awt.Dimension(75, 23));
        applyButton.setMinimumSize(new java.awt.Dimension(75, 23));
        applyButton.setPreferredSize(new java.awt.Dimension(75, 23));
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });
        jPanel4.add(applyButton);

        okButton.setText("OK");
        okButton.setEnabled(false);
        okButton.setMinimumSize(new java.awt.Dimension(75, 23));
        okButton.setPreferredSize(new java.awt.Dimension(75, 23));
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        jPanel4.add(okButton);

        buttonPanel.add(jPanel4, java.awt.BorderLayout.EAST);

        getContentPane().add(buttonPanel, java.awt.BorderLayout.SOUTH);

        associationPanel.setMinimumSize(new java.awt.Dimension(686, 200));
        associationPanel.setPreferredSize(new java.awt.Dimension(930, 200));
        associationPanel.setLayout(new java.awt.GridBagLayout());

        associationTypePanel.setMaximumSize(new java.awt.Dimension(500, 22));
        associationTypePanel.setMinimumSize(new java.awt.Dimension(300, 22));
        associationTypePanel.setPreferredSize(new java.awt.Dimension(300, 22));
        associationTypePanel.setLayout(new java.awt.BorderLayout(15, 5));

        associationTypeComboBox.setFont(new java.awt.Font("SansSerif", 1, 14));
        associationTypeComboBox.setMaximumSize(new java.awt.Dimension(300, 22));
        associationTypeComboBox.setMinimumSize(new java.awt.Dimension(300, 22));
        associationTypeComboBox.setPreferredSize(new java.awt.Dimension(300, 22));
        associationTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                associationTypeComboBoxActionPerformed(evt);
            }
        });
        associationTypePanel.add(associationTypeComboBox, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        associationPanel.add(associationTypePanel, gridBagConstraints);

        jSeparator1.setPreferredSize(new java.awt.Dimension(300, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        associationPanel.add(jSeparator1, gridBagConstraints);

        thisPanel.setMaximumSize(new java.awt.Dimension(300, 45));
        thisPanel.setMinimumSize(new java.awt.Dimension(300, 45));
        thisPanel.setPreferredSize(new java.awt.Dimension(300, 45));
        thisPanel.setLayout(new java.awt.GridBagLayout());

        roleComboBox.setFont(new java.awt.Font("SansSerif", 0, 11));
        roleComboBox.setMaximumSize(new java.awt.Dimension(300, 20));
        roleComboBox.setMinimumSize(new java.awt.Dimension(300, 20));
        roleComboBox.setPreferredSize(new java.awt.Dimension(300, 20));
        roleComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 5, 0);
        thisPanel.add(roleComboBox, gridBagConstraints);

        topicLabel.setText("Topic name");
        topicLabel.setMaximumSize(new java.awt.Dimension(54, 20));
        topicLabel.setMinimumSize(new java.awt.Dimension(54, 20));
        topicLabel.setPreferredSize(new java.awt.Dimension(54, 20));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        thisPanel.add(topicLabel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        associationPanel.add(thisPanel, gridBagConstraints);

        otherPanel.setMaximumSize(new java.awt.Dimension(500, 800));
        otherPanel.setMinimumSize(new java.awt.Dimension(300, 50));
        otherPanel.setPreferredSize(new java.awt.Dimension(300, 50));
        otherPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 15, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        associationPanel.add(otherPanel, gridBagConstraints);

        getContentPane().add(associationPanel, java.awt.BorderLayout.NORTH);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-472)/2, (screenSize.height-300)/2, 472, 300);
    }// </editor-fold>//GEN-END:initComponents

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        running=false;
        cancelled=false;
        synchronized(this){this.notifyAll();}
        try{
            workerThread.join();
        }catch(InterruptedException e){}
        
        if(!shouldCreate()) {
            WandoraOptionPane.showMessageDialog(this,"No association created as one or more fields were left empty!");
            //System.out.println("No association created as one or more fields were empty!");
        }
        else {
            try{
                if(!createAssociation()) cancelled=true;
            }catch(TopicMapException tme){tme.printStackTrace();cancelled=true;}
            try {
                associationTypeBeforeAdd = associationType;
                topicRoleBeforeAdd = topicRole;
                parent.doRefresh();
                workerThread=new Thread(this);
                running=true;
                workerThread.start();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_applyButtonActionPerformed

    private Map<LayeredTopic,Collection<Topic>> resolveRoleMap(LayeredAssociation oldAssociation) throws TopicMapException {
        if(original==null) return null;
        Association selectedOriginal=((LayeredAssociation)original).findAssociationForSelectedLayer();
        if(selectedOriginal==null) return null;
        HashMap<LayeredTopic,Collection<Topic>> newRoleMap=new HashMap<LayeredTopic,Collection<Topic>>();
        for(Topic _role : oldAssociation.getRoles()){
            LayeredTopic role=(LayeredTopic)_role;
            for(Topic selectedRole : selectedOriginal.getRoles()){
                if(role.mergesWithTopic(selectedRole)){
                    Topic player=oldAssociation.getPlayer(role);
                    Topic selectedPlayer=selectedOriginal.getPlayer(selectedRole);
                    if(player.mergesWithTopic(selectedPlayer)){
                        Collection<Topic> c=newRoleMap.get(role);
                        if(c==null){
                            c=new HashSet<Topic>();
                            newRoleMap.put(role,c);
                        }
                        c.add(selectedRole);
                    }
                }
            }
        }
        return newRoleMap;
    }
    
    private void createAssociation2() throws TopicMapException {        
        Association original2=((LayeredAssociation)original).findAssociationForSelectedLayer();
        Collection<Topic> originalRoles=original2.getRoles();
        HashMap<Topic,Topic> originalPlayers=new HashMap<Topic,Topic>();
        for(Topic role : originalRoles){ // original players of the individual association
            originalPlayers.put(role,original2.getPlayer(role));
        }
        TopicMap tm=((LayerStack)(original.getTopicMap())).getSelectedLayer().getTopicMap();
        LayeredTopic type=(LayeredTopic)associationType;

        HashMap roleMap=new HashMap(); // this contains roles mapped to new players
        roleMap.putAll(this.roleMap);  // players may be either TopicSelectLists, Strings
        roleMap.put(topicRole,topic);  // or Topics
        
        // this maps roles of the LayeredAssociation to roles in the individual new Association
        Map<LayeredTopic,Collection<Topic>> newRoleMap=resolveRoleMap((LayeredAssociation)original);
        
        // fill in roles that are not inherited from the original association
        Iterator iter=roleMap.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry e=(Map.Entry)iter.next();
            LayeredTopic role=(LayeredTopic)e.getKey();
            if(newRoleMap.get(role)==null){
                Topic t=role.getTopicForSelectedLayer();
                if(t==null) t=role.copyStubTo(tm);
                Vector<Topic> v=new Vector<Topic>();
                v.add(t);
                newRoleMap.put(role,v);
            }
        }
        
        original2.remove();
        
        // collect used roles from the individual association here
        HashSet<Topic> usedRoles=new HashSet<Topic>();
        
        Association a=null;
        if(type.getTopicsForSelectedLayer().contains(original2.getType())){
            a=tm.createAssociation(original2.getType());
        }
        else{
            Topic st=type.getTopicForSelectedLayer();
            if(st==null) st=type.copyStubTo(tm);
            a=tm.createAssociation(st);
        }
        
        iter=roleMap.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry e=(Map.Entry)iter.next();
            LayeredTopic role=(LayeredTopic)e.getKey();
            Collection<Topic> selectedRoles=newRoleMap.get(role);
            
            Topic newPlayer=null;
            Object o=null;
            if(e.getValue() instanceof Topic) o=e.getValue();
            else{
                TopicSelectList cbox=(TopicSelectList)e.getValue();
                o=cbox.getSelection();
            }
            if(o instanceof Topic){
                LayeredTopic player=(LayeredTopic)o;
                Collection<Topic> sp=player.getTopicsForSelectedLayer();
                for(Topic selectedRole : selectedRoles){
                    Topic originalPlayer=originalPlayers.get(selectedRole);
                    if(originalPlayer!=null && player.getTopicsForSelectedLayer().contains(originalPlayer)){
                        newPlayer=originalPlayer;
                        break;
                    }
                }
                if(newPlayer==null){
                    newPlayer=player.getTopicForSelectedLayer();
                    if(newPlayer==null) newPlayer=player.copyStubTo(tm);
                }
            }
            else{
                String baseName = o.toString();
                if(baseName == null || baseName.length() == 0) baseName="(empty topic)";
                Topic layeredPlayer=original.getTopicMap().getTopicWithBaseName(baseName);
                if(layeredPlayer==null){
                    layeredPlayer=original.getTopicMap().createTopic();
                    layeredPlayer.setBaseName(baseName);
                }
                if(layeredPlayer.getSubjectIdentifiers().isEmpty())
                    layeredPlayer.addSubjectIdentifier(original.getTopicMap().makeSubjectIndicatorAsLocator());
                newPlayer=((LayeredTopic)layeredPlayer).getTopicForSelectedLayer();
            }
            for(Topic selectedRole : selectedRoles){
                usedRoles.add(selectedRole);
                Topic roleClass=SchemaBox.getRoleClass(selectedRole);
                if(!SchemaBox.isInstanceOf(newPlayer,selectedRole)) newPlayer.addType(roleClass);
                a.addPlayer(newPlayer,selectedRole);
            }
        }
        
        // if original association contains roles that haven't been used yet and
        // that merge with some (layered)role of the new association, add old players
        // for them
        for(Map.Entry<Topic,Topic> e : originalPlayers.entrySet()){
            if(!usedRoles.contains(e.getKey())){
                iter=roleMap.entrySet().iterator();
                while(iter.hasNext()){
                    Map.Entry e2=(Map.Entry)iter.next();
                    LayeredTopic role=(LayeredTopic)e2.getKey();
                    if(role.mergesWithTopic(e.getKey())){
                        usedRoles.add(e.getKey());
                        a.addPlayer(e.getValue(),e.getKey());
                        break;
                    }
                }
            }
        }
    }
    
    private boolean createAssociation() throws TopicMapException {
        if(shouldCreate()) {
            if(original!=null && original instanceof LayeredAssociation){
                if( ((LayeredAssociation)original).findAssociationForSelectedLayer()!=null ){
                    createAssociation2();
                    return true;
                }
            }
            if(original!=null) {
                original.remove();
                original = null;
            }
            TopicMap tm=topic.getTopicMap();
            Association a=tm.createAssociation(associationType);
            Topic roleClass=SchemaBox.getRoleClass(topicRole);
            if(!SchemaBox.isInstanceOf(topic,roleClass)) topic.addType(roleClass);
            a.addPlayer(topic,topicRole);
            Iterator iter=roleMap.entrySet().iterator();
            while(iter.hasNext()){
                Map.Entry e=(Map.Entry)iter.next();
//                Topic role=(Topic)e.getKey();
                Topic role=tm.getMergingTopics((Topic)e.getKey()).iterator().next();
                TopicSelectList cbox=(TopicSelectList)e.getValue();
                Object o=cbox.getSelection();
                if(o instanceof Topic){
//                    Topic player=(Topic)o;
                    Topic player=tm.getMergingTopics((Topic)o).iterator().next();
                    roleClass=SchemaBox.getRoleClass(role);
                    if(!SchemaBox.isInstanceOf(player,roleClass)) player.addType(roleClass);
                    a.addPlayer(player,role);
                }
                else {
                    String baseName = o.toString();
                    if(baseName == null || baseName.length() == 0) baseName="(empty topic)";
                    Topic player=tm.getTopicWithBaseName(baseName);
                    if(player==null){
                        player=tm.createTopic();
                        player.setBaseName(baseName);
                    }
                    if(player.getSubjectIdentifiers().isEmpty())
                        player.addSubjectIdentifier(tm.makeSubjectIndicatorAsLocator());
                    roleClass=SchemaBox.getRoleClass(role);
                    if(!SchemaBox.isInstanceOf(player,roleClass)) player.addType(roleClass);
                    a.addPlayer(player,role);
                }
            }
        }
        return true;
    }
    
    
    
    private boolean shouldCreate() {
        boolean shouldCreate = true;
        Iterator iter=roleMap.entrySet().iterator();
        while(iter.hasNext()) {
            try {
                Map.Entry e=(Map.Entry)iter.next();
                TopicSelectList cbox=(TopicSelectList)e.getValue();
                Object o=cbox.getSelection();
                if(! (o instanceof Topic)) {
                    String baseName = o.toString();
                    if(baseName == null || baseName.length() == 0) {
                        shouldCreate = false;
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                shouldCreate = false;
            }
        }
        return shouldCreate;
    }
    
    
    
    public boolean wasCancelled(){
        return cancelled;
    }
    
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        running=false;
        cancelled=false;
        synchronized(this){this.notifyAll();}
        try{
            workerThread.join();
        }catch(InterruptedException e){}
        
        if(!shouldCreate() && associationTypeBeforeAdd == null) {
            WandoraOptionPane.showMessageDialog(this,"No association created as one or more fields were left empty!");
            System.out.print("No association created as one or more fields were empty!");
        }
        else {
            try{
                if(!createAssociation()) cancelled=true;
            }catch(TopicMapException tme){tme.printStackTrace();cancelled=true;}
        }
        this.setVisible(false);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        cancelled=true;
        running=false;
        synchronized(this){this.notifyAll();}
        try{
            workerThread.join();
        }catch(InterruptedException e){}
        this.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void roleComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleComboBoxActionPerformed
        okButton.setEnabled(false);
        applyButton.setEnabled(false);
        synchronized(this){
            newRole=roleComboBox.getSelectedItem();
            this.notifyAll();
        }
    }//GEN-LAST:event_roleComboBoxActionPerformed

    private void associationTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_associationTypeComboBoxActionPerformed
        okButton.setEnabled(false);
        applyButton.setEnabled(false);
        synchronized(this){
            newAssociationType=associationTypeComboBox.getSelectedItem();
            this.notifyAll();
        }
    }//GEN-LAST:event_associationTypeComboBoxActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JPanel associationPanel;
    private javax.swing.JComboBox associationTypeComboBox;
    private javax.swing.JPanel associationTypePanel;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel otherPanel;
    private javax.swing.JComboBox roleComboBox;
    private javax.swing.JPanel thisPanel;
    private javax.swing.JLabel topicLabel;
    // End of variables declaration//GEN-END:variables
    
    public void run(){
        Collection atypes=null;
        try{
            topic.getTopicMap().getTopicsOfType(WandoraManager.ASSOCIATIONTYPE_SI);
            atypes=SchemaBox.getAssociationTypesFor(topic);
        }catch(TopicMapException tme){
            tme.printStackTrace(); // TODO EXCEPTION
            return;
        }
        atypes=TMBox.sortTopics(atypes,null);
        associationTypeComboBox.removeAllItems();
        {
            Iterator iter=atypes.iterator();
            ComboBoxTopicWrapper select=null;
            while(iter.hasNext()){
                Topic t=(Topic)iter.next();
                ComboBoxTopicWrapper wrapper=new ComboBoxTopicWrapper(t);
                try{
                    if(original!=null && t.mergesWithTopic(original.getType())) select=wrapper;
                    if(associationTypeBeforeAdd!=null && t.mergesWithTopic(associationTypeBeforeAdd)) select=wrapper;
                }catch(TopicMapException tme){
                    tme.printStackTrace(); // TODO EXCEPTION;
                    return;
                }
                associationTypeComboBox.addItem(wrapper);
            }
            if(select!=null) associationTypeComboBox.setSelectedItem(select);
        }
        
        Collection typeroles=null;
        while(running){
            Object copiedAType=null;
            Object copiedRole=null;
            synchronized(this){
                while(running && newAssociationType==null && newRole==null){
                    try{
                        this.wait();
                    }catch(InterruptedException e){
                        running=false;
                    }
                }
                copiedAType=newAssociationType;
                copiedRole=newRole;
                newAssociationType=null;
                newRole=null;
            }
            if(!running) break;
            if(copiedAType!=null){
                System.out.println("New association type");
                Topic atype=((ComboBoxTopicWrapper)copiedAType).topic;
                associationType=atype;
                try{
                    typeroles=SchemaBox.getAssociationTypeRoles(atype);
                }catch(TopicMapException tme){tme.printStackTrace();typeroles=new Vector();} // TODO EXCEPTION
                typeroles=TMBox.sortTopics(typeroles,null);
                Collection possibleRoles=new HashSet();
                System.out.println("found total of "+typeroles.size()+" roles for chosen association type");
                Iterator iter=typeroles.iterator();
                while(iter.hasNext()){
                    Topic role=(Topic)iter.next();
                    try{
                        Topic roleClass=SchemaBox.getRoleClass(role);
                        if(SchemaBox.isInstanceOf(topic,roleClass)){
                            possibleRoles.add(role);
                        }
                    }catch(TopicMapException tme){
                        tme.printStackTrace(); // TODO EXCEPTION
                    }
                }
                
                possibleRoles=TMBox.sortTopics(possibleRoles,null);
                Topic selectRole=null;
                if(!possibleRoles.isEmpty()){
                    selectRole=(Topic)possibleRoles.iterator().next();
                }
                if(original!=null){
                    try{
                        Iterator iter2=original.getRoles().iterator();
                        while(iter2.hasNext()){
                            Topic role=(Topic)iter2.next();
                            Topic player=original.getPlayer(role);
                            if(player.mergesWithTopic(topic)){
                                selectRole=role;
                                break;
                            }
                        }
                    }catch(TopicMapException tme){
                        tme.printStackTrace(); // TODO EXCEPTION
                    }
                }
                
                System.out.println("found "+possibleRoles.size()+" possible roles for chosen topic");
                roleComboBox.removeAllItems();
                iter=typeroles.iterator();
//                iter=possibleRoles.iterator();
                ComboBoxTopicWrapper select=null;
                while(iter.hasNext()){
                    Topic t=(Topic)iter.next();
                    ComboBoxTopicWrapper wrapper=new ComboBoxTopicWrapper(t);
                    try{
                        if(selectRole!=null && t != null && !t.isRemoved() && t.mergesWithTopic(selectRole)) select = wrapper;
                        if(topicRoleBeforeAdd != null && topicRoleBeforeAdd.mergesWithTopic(t)) select = wrapper;
                    }catch(TopicMapException tme){
                        tme.printStackTrace(); // TODO EXCEPTION
                    }
                    roleComboBox.addItem(wrapper);
                }
                if(select!=null) roleComboBox.setSelectedItem(select);
            }
            TopicSelectList focusFirstHere = null;
            if(copiedRole!=null){
                System.out.println("New role");
                otherPanel.removeAll();
                otherPanel.setLayout(new java.awt.GridBagLayout());
                Topic trole=((ComboBoxTopicWrapper)copiedRole).topic;
                topicRole=trole;
                roleMap=new HashMap();
                Iterator iter=typeroles.iterator();
                int counter=0;
                while(iter.hasNext()){
                    Topic role=(Topic)iter.next();
                    if(role==trole) continue;
                    java.awt.GridBagConstraints gbc=new java.awt.GridBagConstraints();
                    gbc.gridx=counter;
                    gbc.gridy=0;
                    gbc.fill=gbc.HORIZONTAL;
                    gbc.weightx=1.0;
                    String name="";
                    try{
                        name=role.getBaseName();
                    }catch(TopicMapException tme){
                        tme.printStackTrace(); // TODO EXCEPTION
                        name="Exception retrieving name";
                    }
                    javax.swing.JLabel label=new SimpleLabel(name);
                    label.setFont(UIConstants.smallButtonLabelFont);
                    label.setPreferredSize(new Dimension(300, 23));
                    label.setAlignmentX(label.LEFT_ALIGNMENT);
                    otherPanel.add(label,gbc);

                    gbc=new java.awt.GridBagConstraints();
                    gbc.gridx=counter;
                    gbc.gridy=1;
                    gbc.fill=gbc.HORIZONTAL;
                    gbc.weightx=1.0;
//                    javax.swing.JComboBox cbox=new javax.swing.JComboBox();

                    Collection insts=null;
                    try{
                        insts=SchemaBox.getInstancesOf(SchemaBox.getRoleClass(role));
                    }catch(TopicMapException tme){
                        tme.printStackTrace(); // TODO EXCEPTION
                        insts=new Vector();
                    }
                    insts=TMBox.sortTopics(insts,null);
                    TopicSelectList cbox=new TopicSelectList(insts,true,this);
                    focusFirstHere = cbox;
                    
                    if(original!=null){
                        try{
                            Topic player=original.getPlayer(role);
                            if(player!=null) cbox.setText(player.getBaseName());
                        }catch(TopicMapException tme){
                            tme.printStackTrace(); // TODO EXCEPTION
                        }
                    }
                    
//                    System.out.println("Found "+insts.size()+" instances of topic "+role.getBaseName());
/*                    Iterator iter2=insts.iterator();
                    while(iter2.hasNext()){
                        Topic inst=(Topic)iter2.next();
                        cbox.addItem(new ComboBoxTopicWrapper(inst));
                    }*/

                    roleMap.put(role,cbox);
                    otherPanel.add(cbox,gbc);
                    counter++;
                }
                okButton.setEnabled(true);
                applyButton.setEnabled(true);
            }
            this.validate();
            this.repaint();
            if(focusFirstHere != null) focusFirstHere.requestFocusOnField();
        }
    }
}
