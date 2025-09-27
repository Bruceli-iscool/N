# Variables in N
Variables in N can be declared like this:

    define varname
    var values
    end

For example:

    define x
    + 4 6
    - 3 2
    end

This will result x to having a value of 11 by doing `4+6` added to the difference of `3-2`.

You can reset variables with `set`.

For example:   

    set x
    + 5 2
    end

This will change the value of x from 11 to 7 because it will completely reset the value of the variable. 

To add a value to a variable, see this example:

    set x
    + x 2
    x
    end
This results in a value of 16 (`x=7` so `7+2+7` equals `16`).