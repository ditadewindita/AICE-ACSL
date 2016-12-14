def isTriangle(tri):
    if (tri[0] + tri[1] <= tri[2]) or (tri[1] + tri[2] <= tri[0]) or (tri[0] + tri[2] <= tri[1]):
        return False
    return True


def makeInt(a):
    ret = []
    for b in range(0, 3):
        ret.append(int(a[b]))
    return ret


def getSimilar(s ,a, b):
    st = ["DE", "EF", "DF"]
    b = sorted(b)
    a = sorted(a)
    match = True
    tri = ""
    for i in range(len(a)):
        if b[i] % a[i] == 0 or a[i] % b[i] == 0:
            match &= True
            tri += st[s.rfind(str(b[i]))-3]
        else:
            match &= False
    t = ""
    if not match:
        t = "NONE"
    else:
        t = tri[0:1]
        for x in range(len(tri)):
            if not t.find(tri[x:x+1]) > -1:
                t += tri[x:x+1]
    return t


fileIn = open("TriangleIn.txt")
fileOut = open("TriangleOut.txt", "w")

for line in fileIn:
    s = line.split(", ")
    ss = "".join(s)
    a = makeInt(s[0:3])
    b = makeInt(s[3:])
    x = ""
    if (not isTriangle(a)) or (not isTriangle(b)):
        x = "NOT VALID"
    else:
        x = getSimilar(ss, a, b)
    fileOut.write(x + "\n")
