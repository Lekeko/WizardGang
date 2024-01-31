import greenfoot.*;
public class lvl1 extends level
{
        /** ********************      MAP LEGEND     *********************** */
    //      b = block        m = monster         f = floating platform
    //      p = platform     k = key             w = wall
    //      c = character    d = door            s = score
    public void setFields()
    {
        map = new String[] { "                     pspppppppppppppppppppp",
                             "d m           c    m     ",
                             "ppp            pppppppppp",
                             "                         ",
                             "                  k      p",
                             "     pp                  ",
                             "                        p",
                             "             pppppppppp  ",
                             "                        p",
                             "   f    pp               ",
                             "               m                 p",
                             "    m                    ",
                             "             pppp     m                  p",
                             "            pp           ",
                             "    m                m  p",
                             "         w        w                ppppp",
                             "      p                 p       p",
                             "     pp   p                 p",
                             "    pp    p          pp p  p",
                             "   pp     p     pppp      p",
                             "p  p      p             pp",
                             "pppppppppppp  ppppppppppp",
                             "                        p",
                             "                         ",
                             "ppppppppppppppppppppppppppppppppppppppppppp",};
    }//25,25 map will fit
    public void nextLevel()
    {
        Greenfoot.setWorld(new lvl2());
    }
}
