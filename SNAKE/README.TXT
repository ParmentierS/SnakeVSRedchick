
écriture sur un graf : http://www.siteduzero.com/tutoriel-3-10456-une-histoire-de-conteneur.html#ss_part_3

http://www.commentcamarche.net/forum/affich-1342241-java-supprimer-la-croix-d-une-jframe
//provien de http://www.presence-pc.com/forum/ppc/Programmation/comment-lecteur-audio-java-sujet-2840-1.htm

Toolkit.getDefaultToolkit().addAWTEventListener(
           new AWTEventListener(){
               public void eventDispatched(AWTEvent event){
                   KeyEvent ke = (KeyEvent)event;
                   if(ke.getID() == KeyEvent.KEY_RELEASED){
                       System.out.println("released");
                    }else if(ke.getID() == KeyEvent.KEY_PRESSED){
                        System.out.println("pressed"+ke.getKeyCode());
                    }else if(ke.getID() == KeyEvent.KEY_TYPED){
                        System.out.println("typed");
                    }else if(ke.getKeyCode() == KeyEvent.VK_UP){
                        System.out.println("up");
                    }
                    switch (ke.getKeyCode()) 
                 { 
                     case KeyEvent.VK_UP     : System.out.println("up"); break; 
                     case KeyEvent.VK_LEFT   : System.out.println("LEFT"); break; 
                     case KeyEvent.VK_DOWN   : System.out.println("DOWN"); break; 
                     case KeyEvent.VK_RIGHT  : System.out.println("RIGHT"); break;
                 }
                }
            }, AWTEvent.KEY_EVENT_MASK);

            
            
            private class listenerwindow implements WindowEvent
    {
    /**
     * @pre :
     * @post:
     *        
     */
          public void windowClosing(WindowEvent e) {
              int rep=JOptionPane.showConfirmDialog(null,"Do you really want to close this beatiful software???","DON'T EVEN THINK ABOUT IT!",JOptionPane.YES_NO_OPTION); 
              if(rep == 0)    System.exit(0);
        
            }
     }