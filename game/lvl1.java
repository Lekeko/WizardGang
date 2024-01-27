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
                             "ppp                      ",
                             "                         ",
                             "                         ",
                             "     pp                  ",
                             "                         ",
                             "             pppppppppppp",
                             "                         ",
                             "   f    pp               ",
                             "               m         ",
                             "    m                    ",
                             "             pppp     m  ",
                             "                         ",
                             "    m                m   ",
                             "         w        w      ",
                             "      p                  ",
                             " k   pp   p              ",
                             "    pp    p           pp ",
                             "   pp     p     pppp     ",
                             "   p      p              ",
                             "ppppppppppppppppppppppppp", };
    }
    public void nextLevel()
    {
        Greenfoot.setWorld(new lvl2());
    }
}
