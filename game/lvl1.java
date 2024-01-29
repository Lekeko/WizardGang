import greenfoot.*;
public class lvl1 extends level
{
        /** ********************      MAP LEGEND     *********************** */
    //      b = block        m = monster         f = floating platform
    //      p = platform     k = key             w = wall
    //      c = character    d = door            s = score
    public void setFields()
    {
        map = new String[] { "                      s  ",
                             "d m           c    m     ",
                             "ppp            pppppppppp",
                             "                         ",
                             "                  k      ",
                             "     pp                  ",
                             "                         ",
                             "             pppppppppp  ",
                             "                         ",
                             "   f    pp               ",
                             "               m         ",
                             "    m                    ",
                             "             pppp     m  ",
                             "            pp           ",
                             "    m                m   ",
                             "         w        w      ",
                             "      p                  ",
                             "     pp   p              ",
                             "    pp    p           pp ",
                             "   pp     p     pppp     ",
                             "   p      p              ",
                             "pppppppppppp  ppppppppppp",
                             "                         ",
                             "                         ",
                             "ppppppppppppppppppppppppp",};
    }//25,25 map will fit
    public void nextLevel()
    {
        Greenfoot.setWorld(new lvl2());
    }
}
