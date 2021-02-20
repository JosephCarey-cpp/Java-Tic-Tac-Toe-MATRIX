/*
Name: Joseph Carey
Program: Final Project TicTacToe.java
Date: 11/02/2020 - 11/24/2020
Description: This program shows a tic tac toe game with a main menu. The user can select the tutorial button, which
             shows a button to bring them back to the main menu, and a new game button to start the game. Once the game
             is completed the program will close itself.
*/
// To Debug Compile in terminal type: javac TicTacToe.java
// To Debug Run the program in terminal type java TicTacToe
// To Debug Compile & Run at the same time type in the terminal: compile TicTacToe
// NOTE: must have compile.bat in order to do above
// NOTE: Using jar exectuable, for best practices this file must be debugged
//       before adding to final JAR program
import javax.swing.*;
import java.awt.event.*; // this is needed for the event handlers
import java.awt.Container; // need this to add controls
import java.awt.*; // need this for the layout manager

public class TicTacToeMatrix extends JFrame
{
    // all components
    ///  PROGRAM FRAME COMPONENTS \\\
    private JFrame mainFrame;
    private JLabel borderFrameX; // a label to make a border along the x axis for the screen
    private Container c;

    /// TITLE COMPONETS \\\
    private ImageIcon img;
    private JLabel imgLabel;
    private JLabel titleSpacer; // a spacer between the title image and the buttons on each menu

    /// SUB-TITLE COMPONENTS \\\
    // each image corresponds to a label for it to be displayed on screen
    private ImageIcon imgHowTo;
    private JLabel HowToLabel;
    private JLabel subtitleSpacer; // a spacer between the buttons and the sub-title
    /// NOTE: How To Play menu DOES NOT require this spacer, see mock up for details

    /// MAIN MENU COMPONENTS \\\
    private JLabel  mainMenuSpacer1; // a spacer between the buttons in the main menu which is 40 px
    private JButton NewGameButton;
    private JButton HowToButton;

    /// HOW TO PLAY MENU COMPONENTS \\\
    private JLabel tutorLabel;
    private ImageIcon imgTutorial;
    private JButton HTtoMainButton; // back button from How To Play Menu to Main Menu

    /// GAME STAGE COMPONENTS \\\
    //added for the first JPanel:
    private JPanel ScoreBoard;
    private JLabel Spacer1;
    private JLabel Spacer2;
    private JLabel P1ScoreLabel;
    private JLabel SideNumLabel;
    private JLabel P2ScoreLabel;

    // added for the second JPanel
    private JPanel CubeSide;
    private JButton gridArrayButtons[][] = {
            {new JButton(" "), new JButton(" "), new JButton(" ")},
            {new JButton(" "), new JButton(" "), new JButton(" ")},
            {new JButton(" "), new JButton(" "), new JButton(" ")},
    };
    private int xoStart = 0; // player 1 starts initially with 0, player 2 is 1
    private int score1 = 0; // the first player's score, O
    private int score2 = 0; // the second player's score, X
    private int sideNum = 1; // the side number displayed
    private int tieCount = 0; // a tie counter
    private ImageIcon tileX;
    private ImageIcon tileO;
    private ImageIcon tileXWin;
    private ImageIcon tileOWin;

    /// CONSTRUCTOR \\\
    public TicTacToeMatrix()
    {
        mainFrame = new JFrame("Tic Tac Toe MATRIX v.1.1");

        // create objects
        // Phase 1 - Step 1 (part 2): Instantiate objects

        /// PROGRAM FRAME \\\
        borderFrameX = new JLabel(" ")
        {
            {
                setSize(400,20);
                setMaximumSize(getSize());
            }
        };

        /// TITLE \\\
        titleSpacer = new JLabel(" ");
        {
            {
                setSize(400,44);
                setMaximumSize(getSize());
            }
        };
        img = new ImageIcon(getClass().getResource("ScreenImage_257x186.png"));
        imgLabel = new JLabel(img);

        /// SUB-TITLE \\\
        subtitleSpacer = new JLabel(" ")
        {
            {
                setSize(400,52);
                setMaximumSize(getSize());
            }
        };

        imgHowTo = new ImageIcon(getClass().getResource("HowTo400x80.png"));
        HowToLabel = new JLabel(imgHowTo);

        /// MAIN MENU \\\
        mainMenuSpacer1 = new JLabel(" ")
        {
            {
                setSize(800,40);
                setMaximumSize(getSize());
            }
        };

        NewGameButton = new JButton("New Game")
        {
            {
                setSize(400,80);
                setMaximumSize(getSize());
            }
        };

        HowToButton = new JButton("How To Play")
        {
            {
                setSize(400,80);
                setMaximumSize(getSize());
            }
        };


        /// HOW TO PLAY MENU \\\
        imgTutorial= new ImageIcon(getClass().getResource("tutorImage400x292.png"));
        tutorLabel = new JLabel(imgTutorial);

        HTtoMainButton = new JButton("Back to Main Menu")
        {
            {
                setSize(400,80);
                setMaximumSize(getSize());
            }
        };

        /// GAME STAGE \\\
        //first JPanel:
        JPanel ScoreBoard = new JPanel()
        {
            {
                setSize(700,60);
                setMaximumSize(getSize());
                setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
                setVisible(false);
            }
        };


        P1ScoreLabel = new JLabel(" Player O Score: 0") // put the object P1ScoreImg in the label " "
        {
            {
                setSize(150,60);
                setMaximumSize(getSize());
            }
        };

        Spacer1 = new JLabel(" ")
        {
            {
                setSize(33,60);
                setMaximumSize(getSize());
            }
        };

        SideNumLabel = new JLabel(" Side Number: 1")  // put the object SideNumImg in the label " "
        {
            {
                setSize(150,60);
                setMaximumSize(getSize());
            }
        };

        Spacer2 = new JLabel(" ")
        {
            {
                setSize(33,60);
                setMaximumSize(getSize());
            }
        };

        P2ScoreLabel = new JLabel(" Player X Score: 0") // put the object P2ScoreImg in the label " "
        {
            {
                setSize(150,60);
                setMaximumSize(getSize());
            }
        };

        // second JPanel:
        JPanel CubeSide = new JPanel()
        {
            {
                setSize(700,700);
                setMaximumSize(getSize());
                setLayout(new GridLayout(3,3,25,25));
                setVisible(false);
            }
        };
        tileX = new ImageIcon(getClass().getResource("TileGreen_X_Black.png"));
        tileO = new ImageIcon(getClass().getResource("TileGreen_O_Black.png"));
        tileXWin = new ImageIcon(getClass().getResource("TileGreen_X_GreenWin.png"));
        tileOWin = new ImageIcon(getClass().getResource("TileGreen_O_GreenWin.png"));


        // get the conent pane and specify the layout manager
        // Phase 1 - Step 2 (part 1) : Get the content pane of JFrame
        Container c = mainFrame.getContentPane();
        c.setLayout(new BoxLayout(c,BoxLayout.Y_AXIS));
        mainFrame.setSize(1000,1000);
        c.setBackground(new Color(0,0,0));

        // add the objects to the Container
        // Phase 1 - Step 2 (part 2): Add the objects to the content pane of JFrame
        /* DEV NOTES:
        * At the beginning of menus, a title and subtitle has to be added with
        * a spacer, so each new menu created does not need to start with a
        * menu type of spacer
        *
        * each button/label added for a menu must:
        *       - be aligned to the center
        *       - be of size 400x80
        *       - have a beveled border (for buttons)
        *       - the color must be (145,145,145) (for buttons)
        *       - have a spacer for the next button
        * PROCESS:
        *       - add the button
        *       - set the color
        *       - create the custom border
        *       - align the button on the page
        *       - use a JLabel as a spacer and add it
        *
        * each image must:
        *       - be aligned to the center
        *       - be added to its corresponding label (see IMAGES and object calls)
        * PROCESS:
        *       - add the image
        *       - align it on the screen
        *
        * at the end of each title/menu and set of componets related must be
        * invisible since the program starts with an aimation
        * */

        ///--ANIMATION OBJECTS----------------------------------------------\\\

        ///-----------------------------------------------------------------\\\


        ///--Top Border for program frame--------------------------------------------------------\\\
        c.add(borderFrameX);
        borderFrameX.setAlignmentX(Component.CENTER_ALIGNMENT);
        /// --------------------------------------------------------------------------------------\\\


        ///--TITLE OBJECTS FOR ALL MENUS--------------------------------\\\
        c.add(imgLabel); // adding image
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //aligning image
        c.add(titleSpacer); // spacer
        titleSpacer.setAlignmentX(Component.CENTER_ALIGNMENT);

        // visiblity is set to false initially for the animation
        imgLabel.setVisible(false);
        titleSpacer.setVisible(false);
        ///-----------------------------------------------------------\\\


        ///--SUB-TITLE OBJECTS FOR ALL MENUS--------------------------------\\\
        c.add(HowToLabel);
        HowToLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //aligning image
        c.add(subtitleSpacer);
        subtitleSpacer.setAlignmentX(Component.CENTER_ALIGNMENT); //aligning  label

        // visibility set to false initially
        HowToLabel.setVisible(false);
        subtitleSpacer.setVisible(false);
        ///-----------------------------------------------------------------\\\


        ///--MAIN MENU SCREEN ELEMENTS-----------------------------------------\\\
        c.add(NewGameButton);                                // add the button
        NewGameButton.setBackground(new Color(145,145,145)); // set the color
        NewGameButton.setBorder(BorderFactory.createRaisedBevelBorder()); // create the custom border
        NewGameButton.setAlignmentX(Component.CENTER_ALIGNMENT); // align the button
        c.add(mainMenuSpacer1);          // and set a spacer between the buttons
        mainMenuSpacer1.setAlignmentX(Component.CENTER_ALIGNMENT);

        c.add(HowToButton);
        HowToButton.setBackground(new Color(145,145,145));
        HowToButton.setBorder(BorderFactory.createRaisedBevelBorder());
        HowToButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        // create ToolTips and shortcuts
        NewGameButton.setToolTipText("Click to Start New Game");
        NewGameButton.setMnemonic('n');

        HowToButton.setToolTipText("Click to See Tutorial");
        HowToButton.setMnemonic('h');

        // create and register the object event handlers
        // Phase 2 - Step 2: Create instances of the event handler class (a listener class)
        NewGameButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                int choice;
                choice = JOptionPane.showConfirmDialog(null, "Make a new game?",
                        "New Game Prompt", JOptionPane.YES_NO_OPTION);
                if(choice == 0)
                {
                    ShowMainMenu(false); // set the title and main menu to invisible
                    ShowTitle(false);
                    c.add(ScoreBoard);          // add the panels
                    c.add(CubeSide);
                    ScoreBoard.setVisible(true); // set them to be visible and repective components
                    CubeSide.setVisible(true);
                    ShowGameStage(true);
                }
            }
        });
        HowToEventHandler hHandler = new HowToEventHandler();

        // Phase 2 - Step 3: Register the handler class (a listener class)
        // above did not require an event handler and inner class due to threading errors for JPanel

        HowToButton.addActionListener(hHandler);

        // initial visiblity
        mainMenuSpacer1.setVisible(false);
        NewGameButton.setVisible(false);
        HowToButton.setVisible(false);
        ///------------------------------------------------------------------------------------------\\\


        ///--HOW TO PLAY MENU SCREEN ELEMENTS--------------------------------------\\\
        c.add(tutorLabel); // add the label
        tutorLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // align the label
        c.add(HTtoMainButton); // add the button
        HTtoMainButton.setBackground(new Color(145,145,145)); // set the color
        HTtoMainButton.setBorder(BorderFactory.createRaisedBevelBorder()); // create the custom border
        HTtoMainButton.setAlignmentX(Component.CENTER_ALIGNMENT); // align the button

        // create ToolTips and shortcuts
        HTtoMainButton.setToolTipText("Go Back to Main Menu");
        HTtoMainButton.setMnemonic('b');

        // create and register the object event handlers
        // Phase 2 - Step 2: Create instances of the event handler class (a listener class)
        HTtoMainEventHandler htHandler = new HTtoMainEventHandler();

        // Phase 2 - Step 3: Register the handler class (a listener class)// Phase 2 - Step 3: Register the handler class (a listener class)
        HTtoMainButton.addActionListener(htHandler);

        // initial visiblity
        tutorLabel.setVisible(false);
        HTtoMainButton.setVisible(false);
        ///------------------------------------------------------------------------\\\

        ///--GAME STAGE ELEMENTS---------------------------------------------------\\\
        c.add(ScoreBoard); // add the panel
        ScoreBoard.setAlignmentX(Component.CENTER_ALIGNMENT); // align the panel to the center
        ScoreBoard.setBackground(new Color(0,0,0));
        ScoreBoard.add(P1ScoreLabel); // add the label
        P1ScoreLabel.setBackground(new Color(145,145,145)); // give it a background color
        ScoreBoard.add(Spacer1); // add the spacer
        ScoreBoard.add(SideNumLabel); // add the label
        SideNumLabel.setBackground(new Color(145,145,145));
        ScoreBoard.add(Spacer2); // add the spacer
        ScoreBoard.add(P2ScoreLabel); // add the label
        P2ScoreLabel.setBackground(new Color(145,145,145));

        c.add(CubeSide); // add the panel
        CubeSide.setAlignmentX(Component.CENTER_ALIGNMENT); // align the panel to the center
        CubeSide.setBackground(new Color(0,0,0)); // starting side is green

        //add the button array to the panel
        for (int i=0; i <3; i++)
        {
            for (int j=0; j <3; j++)
            {
                CubeSide.add(gridArrayButtons[i][j]);
                gridArrayButtons[i][j].setSize(200,200);
                gridArrayButtons[i][j].setMaximumSize(getSize());
                gridArrayButtons[i][j].setBackground(new Color(56,131,56)); // give it a background color
                gridArrayButtons[i][j].setBorder(BorderFactory.createRaisedBevelBorder()); // create the custom border
            }
        }

        // create and register the object event handlers
        // Phase 2 - Step 2: Create instances of the event handler class (a listener class)
        GridButtonsHandler gbHandler = new GridButtonsHandler();
        for (int i=0; i <3; i++)
        {
            for (int j=0; j <3; j++)
            {
                gridArrayButtons[i][j].addActionListener(gbHandler);
            }
        }

        // initial visiblity
        P1ScoreLabel.setVisible(false);
        Spacer1.setVisible(false);
        SideNumLabel.setVisible(false);
        Spacer2.setVisible(false);
        P2ScoreLabel.setVisible(false);

        for (int i=0; i <3; i++)
        {
            for (int j=0; j <3; j++)
            {
                gridArrayButtons[i][j].setVisible(false);
            }
        }
        ///------------------------------------------------------------------------\\\


        // define and register window event handlers
        // Phase 2 - Step 1: Write the code for an event handler cllass (a listener class)
        mainFrame.addWindowListener(new WindowAdapter()
        {//anonymous class
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        mainFrame.setVisible(true);

        // start of the program
        ShowTitle(true);
        ShowMainMenu(true);
    }

    // inner class for the HowToButton event handler
    class HowToEventHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // debug to check and see it works
            //JOptionPane.showMessageDialog(null, "How To Play was pressed",
            //        "HowToEventHandler Message", JOptionPane.INFORMATION_MESSAGE);
            ShowMainMenu(false);
            ShowHowToMenu(true);
        }
    }//end of listener class

    // inner class for the HTtoMainButton event handler
    class HTtoMainEventHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // debug to check and see it works
            //JOptionPane.showMessageDialog(null, "HT to Main Button was pressed",
            //        "HTtoMainEventHandler Message", JOptionPane.INFORMATION_MESSAGE);
            ShowHowToMenu(false);
            ShowMainMenu(true);
        }
    }//end of listener class

    // inner class for the Grid Array Buttons event handler
    class GridButtonsHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            int counteri = 0;
            int counterj = 0;

            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (e.getSource() == gridArrayButtons[i][j])
                    { // store the location of the button when clicked
                        counteri = i;
                        counterj = j;
                    }
                }
            }

            if (xoStart == 1) // xoStart is 1 when it is X's turn, set every icon to the specified image
            {
                // to make sure the button is clicked only once, this checks to see
                // if there is no icon (==null) or if it has not been set to 0
                if (gridArrayButtons[counteri][counterj].getIcon() == null ||
                        gridArrayButtons[counteri][counterj].getIcon() != tileO)
                {
                    gridArrayButtons[counteri][counterj].setIcon(tileX);
                    gridArrayButtons[counteri][counterj].setText("");
                    xoStart = 0;
                }
            }

            else if (xoStart == 0) // xoStart is 0 when it is O's turn, set every icon to the specified image
            {
                // to make sure the button is clicked only once, this checks to see
                // if there is no icon (==null) or if it has not been set to 0
                if (gridArrayButtons[counteri][counterj].getIcon() == null ||
                        gridArrayButtons[counteri][counterj].getIcon() != tileX)
                {
                    gridArrayButtons[counteri][counterj].setIcon(tileO);
                    gridArrayButtons[counteri][counterj].setText("");
                    xoStart = 1;
                }
            }

            // check to see if there is a winner by rows with the icons assoicated
            for (int i = 0; i < 3; i++)
            {
                // needed for null pointer exceptions
                if (gridArrayButtons[i][0].getIcon() != null
                        && gridArrayButtons[i][1].getIcon() != null
                        && gridArrayButtons[i][2].getIcon() != null)
                {
                    if (gridArrayButtons[i][0].getIcon().toString() == gridArrayButtons[i][1].getIcon().toString()
                            && gridArrayButtons[i][1].getIcon().toString() == gridArrayButtons[i][2].getIcon().toString())
                    {
                        if (gridArrayButtons[i][2].getIcon() == tileX)
                        {
                            for (int a = 0; a < 3; a++)
                            {
                                gridArrayButtons[i][a].setIcon(tileXWin);
                            }
                            JOptionPane.showMessageDialog(null, "X Wins!!! Flipping side....",
                                    "Winner Message", JOptionPane.INFORMATION_MESSAGE);
                            score2++;
                            sideNum++;
                            P2ScoreLabel.setText(" Player X Score: "+ score2);
                            SideNumLabel.setText(" Side Number: " + sideNum);
                            ChangeTileColor(sideNum);
                        }
                        if (gridArrayButtons[i][2].getIcon() == tileO)
                        {
                            for (int a = 0; a < 3; a++)
                            {
                                gridArrayButtons[i][a].setIcon(tileOWin);
                            }
                            JOptionPane.showMessageDialog(null, "O Wins!!! Flipping side....",
                                    "Winner Message", JOptionPane.INFORMATION_MESSAGE);
                            score1++;
                            sideNum++;
                            P1ScoreLabel.setText(" Player X Score: "+ score1);
                            SideNumLabel.setText(" Side Number: " + sideNum);
                            ChangeTileColor(sideNum);
                        }
                    }
                }
            }

            // check to see if there is a winner by collums with the icons assoicated
            for(int i=0; i<3; i++)
            {
                // needed for null pointer exceptions
                if(gridArrayButtons[0][i].getIcon() != null
                        && gridArrayButtons[1][i].getIcon() != null
                        && gridArrayButtons[2][i].getIcon() != null)
                {
                    if(gridArrayButtons[0][i].getIcon().toString() == gridArrayButtons[1][i].getIcon().toString()
                            && gridArrayButtons[1][i].getIcon().toString() == gridArrayButtons[2][i].getIcon().toString())
                    {
                        if (gridArrayButtons[2][i].getIcon() == tileX)
                        {
                            for (int a = 0; a < 3; a++)
                            {
                                gridArrayButtons[a][i].setIcon(tileXWin);
                            }
                            JOptionPane.showMessageDialog(null, "X Wins!!! Flipping side....",
                                    "Winner Message", JOptionPane.INFORMATION_MESSAGE);
                            score2++;
                            sideNum++;
                            P2ScoreLabel.setText(" Player X Score: "+ score2);
                            SideNumLabel.setText(" Side Number: " + sideNum);
                            ChangeTileColor(sideNum);
                        }
                        if (gridArrayButtons[2][i].getIcon() == tileO)
                        {
                            for (int a = 0; a < 3; a++)
                            {
                                gridArrayButtons[a][i].setIcon(tileOWin);
                            }
                            JOptionPane.showMessageDialog(null, "O Wins!!! Flipping side....",
                                    "Winner Message", JOptionPane.INFORMATION_MESSAGE);
                            score1++;
                            sideNum++;
                            P1ScoreLabel.setText(" Player O Score: "+ score1);
                            SideNumLabel.setText(" Side Number: " + sideNum);
                            ChangeTileColor(sideNum);
                        }
                    }
                }
            }

            // check to see if there is a winner by diagonal
            // (left top corner to right bottom corner)   \  with the icons assoicated
            // needed for null pointer exceptions
            if(gridArrayButtons[1][1].getIcon() != null
                    && gridArrayButtons[0][0].getIcon() != null
                    && gridArrayButtons[2][2].getIcon() != null)
            {
                if(gridArrayButtons[0][0].getIcon().toString() == gridArrayButtons[1][1].getIcon().toString()
                        && gridArrayButtons[1][1].getIcon().toString() == gridArrayButtons[2][2].getIcon().toString())
                {
                    if (gridArrayButtons[2][2].getIcon() == tileX)
                    {
                        gridArrayButtons[0][0].setIcon(tileXWin);
                        gridArrayButtons[1][1].setIcon(tileXWin);
                        gridArrayButtons[2][2].setIcon(tileXWin);
                        JOptionPane.showMessageDialog(null, "X Wins!!! Flipping side....",
                                "Winner Message", JOptionPane.INFORMATION_MESSAGE);
                        score2++;
                        sideNum++;
                        P2ScoreLabel.setText(" Player X Score: "+ score2);
                        SideNumLabel.setText(" Side Number: " + sideNum);
                        ChangeTileColor(sideNum);
                    }
                    if (gridArrayButtons[2][2].getIcon() == tileO)
                    {
                        gridArrayButtons[0][0].setIcon(tileOWin);
                        gridArrayButtons[1][1].setIcon(tileOWin);
                        gridArrayButtons[2][2].setIcon(tileOWin);
                        JOptionPane.showMessageDialog(null, "O Wins!!! Flipping side....",
                                "Winner Message", JOptionPane.INFORMATION_MESSAGE);
                        score1++;
                        sideNum++;
                        P1ScoreLabel.setText(" Player O Score: "+ score1);
                        SideNumLabel.setText(" Side Number: " + sideNum);
                        ChangeTileColor(sideNum);
                    }
                }
            }

            // check to see if there is a winner by diagonal
            // (right top corner to left bottom corner)  /  with the icons assoicated
            // needed for null pointer exceptions
            else if(gridArrayButtons[1][1].getIcon() != null
                    && gridArrayButtons[2][0].getIcon() != null
                    && gridArrayButtons[0][2].getIcon() != null)
            {
                if(gridArrayButtons[2][0].getIcon().toString() == gridArrayButtons[1][1].getIcon().toString()
                        && gridArrayButtons[1][1].getIcon().toString() == gridArrayButtons[0][2].getIcon().toString())
                {
                    if (gridArrayButtons[1][1].getIcon() == tileX)
                    {
                        gridArrayButtons[2][0].setIcon(tileXWin);
                        gridArrayButtons[1][1].setIcon(tileXWin);
                        gridArrayButtons[0][2].setIcon(tileXWin);
                        JOptionPane.showMessageDialog(null, "X Wins!!! Flipping side....",
                                "Winner Message", JOptionPane.INFORMATION_MESSAGE);
                        score2++;
                        sideNum++;
                        P2ScoreLabel.setText(" Player X Score: "+ score2);
                        SideNumLabel.setText(" Side Number: " + sideNum);
                        ChangeTileColor(sideNum);
                    }
                    if (gridArrayButtons[1][1].getIcon() == tileO)
                    {
                        gridArrayButtons[2][0].setIcon(tileOWin);
                        gridArrayButtons[1][1].setIcon(tileOWin);
                        gridArrayButtons[0][2].setIcon(tileOWin);
                        JOptionPane.showMessageDialog(null, "O Wins!!! Flipping side....",
                                "Winner Message", JOptionPane.INFORMATION_MESSAGE);
                        score1++;
                        sideNum++;
                        P1ScoreLabel.setText(" Player O Score: "+ score1);
                        SideNumLabel.setText(" Side Number: " + sideNum);
                        ChangeTileColor(sideNum);
                    }
                }
            }

            // this checks are used to determine if a game has tied
            // needed for null pointer exceptions
            // I was getting errors to automate this section with nested loops,
            // hence why the arguements in the if statement are bulky
            if (gridArrayButtons[0][0].getIcon() != null
                    && gridArrayButtons[0][1].getIcon() != null
                    && gridArrayButtons[0][2].getIcon() != null
                    && gridArrayButtons[1][0].getIcon() != null
                    && gridArrayButtons[1][1].getIcon() != null
                    && gridArrayButtons[1][2].getIcon() != null
                    && gridArrayButtons[2][0].getIcon() != null
                    && gridArrayButtons[2][1].getIcon() != null
                    && gridArrayButtons[2][2].getIcon() != null)
            {
                JOptionPane.showMessageDialog(null, "Tie!! No one wins. Flipping side....",
                                "Winner Message", JOptionPane.INFORMATION_MESSAGE);
                sideNum++;
                tieCount++;
                ChangeTileColor(sideNum);
            }

            // checks the side scores to determine the overall winner
            if (score1 == 4 || (score1 > score2 && sideNum == 7))
            {
                JOptionPane.showMessageDialog(null, "O Wins with " + score1 + " Sides out of 6! Closing program......"
                        ,"MATRIX Winner Message", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }

            else if (score2 == 4 || (score2 > score1 && sideNum == 7))
            {
                JOptionPane.showMessageDialog(null, "X Wins with " + score2 + " Sides out of 6! Closing program......"
                        ,"MATRIX Winner Message", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }

            else if (score1 == score2 && sideNum == 7)
            {
                JOptionPane.showMessageDialog(null, "Tie! No One Wins with " + tieCount +" ties and "
                                +score1 +" sides taken equally divided. Closing program.....",
                                "MATRIX Winner Message", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        }
    }

    /// METHODS \\\
    public void ShowTitle(boolean choice) // call to show/hide the title elements
    {
        imgLabel.setVisible(choice);
        titleSpacer.setVisible(choice);
    }

    public void ShowMainMenu(boolean choice) // call to show/hide the main menu elements
    {
        mainMenuSpacer1.setVisible(choice);
        NewGameButton.setVisible(choice);
        HowToButton.setVisible(choice);
    }

    public void ShowHowToMenu(boolean choice) // call to show/hide the how to play menu elements
    {
        HowToLabel.setVisible(choice);
        tutorLabel.setVisible(choice);
        HTtoMainButton.setVisible(choice);
    }

    public void ShowGameStage(boolean choice)
    {
        P1ScoreLabel.setVisible(choice);
        Spacer1.setVisible(choice);
        SideNumLabel.setVisible(choice);
        Spacer2.setVisible(choice);
        P2ScoreLabel.setVisible(choice);
        for (int i=0; i <3; i++)
        {
            for (int j=0; j <3; j++)
            {
                gridArrayButtons[i][j].setVisible(choice);
            }
        }
    }


    public void ChangeTileColor(int sideNum)
    {
        if (sideNum == 2)
        {
            SideNumLabel.setText(" Side Number: 2");
            tileX = new ImageIcon(getClass().getResource("TileRed_X_Black.png"));
            tileO = new ImageIcon(getClass().getResource("TileRed_O_Black.png"));
            tileXWin = new ImageIcon(getClass().getResource("TileRed_X_RedWin.png"));
            tileOWin = new ImageIcon(getClass().getResource("TileRed_O_RedWin.png"));

            for (int i=0; i <3; i++)
            {
                for (int j=0; j <3; j++)
                {
                    gridArrayButtons[i][j].setIcon(null);
                    gridArrayButtons[i][j].setBackground(new Color(136,0,27)); // give it a background color
                }
            }
        }

        else if (sideNum == 3)
        {
            tileX = new ImageIcon(getClass().getResource("TileYellow_X_Black.png"));
            tileO = new ImageIcon(getClass().getResource("TileYellow_O_Black.png"));
            tileXWin = new ImageIcon(getClass().getResource("TileYellow_X_YellowWin.png"));
            tileOWin = new ImageIcon(getClass().getResource("TileYellow_O_YellowWin.png"));

            for (int i=0; i <3; i++)
            {
                for (int j=0; j <3; j++)
                {
                    gridArrayButtons[i][j].setIcon(null);
                    gridArrayButtons[i][j].setBackground(new Color(255,242,56)); // give it a background color
                }
            }
        }

        else if (sideNum == 4)
        {
            tileX = new ImageIcon(getClass().getResource("TileOrange_X_Black.png"));
            tileO = new ImageIcon(getClass().getResource("TileOrange_O_Black.png"));
            tileXWin = new ImageIcon(getClass().getResource("TileOrange_X_OrangeWin.png"));
            tileOWin = new ImageIcon(getClass().getResource("TileOrange_O_OrangeWin.png"));

            for (int i=0; i <3; i++)
            {
                for (int j=0; j <3; j++)
                {
                    gridArrayButtons[i][j].setIcon(null);
                    gridArrayButtons[i][j].setBackground(new Color(255,127,56)); // give it a background color
                }
            }
        }

        else if (sideNum == 5)
        {
            tileX = new ImageIcon(getClass().getResource("TileBlue_X_Black.png"));
            tileO = new ImageIcon(getClass().getResource("TileBlue_O_Black.png"));
            tileXWin = new ImageIcon(getClass().getResource("TileBlue_X_BlueWin.png"));
            tileOWin = new ImageIcon(getClass().getResource("TileBlue_O_BlueWin.png"));

            for (int i=0; i <3; i++)
            {
                for (int j=0; j <3; j++)
                {
                    gridArrayButtons[i][j].setIcon(null);
                    gridArrayButtons[i][j].setBackground(new Color(63,72,204)); // give it a background color
                }
            }
        }

        else if (sideNum == 6)
        {
            tileX = new ImageIcon(getClass().getResource("TileWhite_X_Black.png"));
            tileO = new ImageIcon(getClass().getResource("TileWhite_O_Black.png"));
            tileXWin = new ImageIcon(getClass().getResource("TileWhite_X_WhiteWin.png"));
            tileOWin = new ImageIcon(getClass().getResource("TileWhite_O_WhiteWin.png"));

            for (int i=0; i <3; i++)
            {
                for (int j=0; j <3; j++)
                {
                    gridArrayButtons[i][j].setIcon(null);
                    gridArrayButtons[i][j].setBackground(new Color(255,255,255)); // give it a background color
                }
            }
        }
    }

    public static void main(String[] args)
    {
        new TicTacToeMatrix(); // starts the program and calls the constructor
    }
}
/* Program Test Run:
C:\Users\Joseph Carey\Desktop\Fall2020_CSIS24_programs\TicTacToeMatrix>compile TicTacToeMatrix

C:\Users\Joseph Carey\Desktop\Fall2020_CSIS24_programs\TicTacToeMatrix>javac TicTacToeMatrix.java

C:\Users\Joseph Carey\Desktop\Fall2020_CSIS24_programs\TicTacToeMatrix>java TicTacToeMatrix
*/