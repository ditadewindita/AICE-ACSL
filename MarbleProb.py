def probability(red, blue, white, replacement, colorarr):
    total = red + blue + white
    n = 1
    d = 1
    for m in colorarr:
        if m == 'R':
            n *= red
        if m == 'B':
            n *= blue
        if m == 'W':
            n *= white
        if m == 'r':
            n *= abs(total-red)
        if m == 'b':
            n *= abs(total-blue)
        if m == 'w':
            n *= abs(total-white)
        d *= total
        if replacement == 'N':
            total -= 1
    return [n, d]


for i in range(0, 5):
    s = input().split(", ")
    numMarbles = s[0]
    red = s[1]
    blue = s[2]
    white = s[3]
    colorArr = s[4]
    replacement = 'X'
    if int(numMarbles) > 1:
        replacement = s[4]
        colorArr = s[5:]
    p = probability(int(red), int(blue), int(white), replacement, colorArr)
    print(str(p[0])+'/'+str(p[1]))




