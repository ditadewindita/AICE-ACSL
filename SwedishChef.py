import collections

d = collections.OrderedDict()
d['THE'] = 'zee'
d['AN'] = 'un'
d['AU'] = 'oo'
d['A '] = 'a '  #
d['A'] = 'e'
d['OW'] = 'oo'
d['O'] = 'u'
d['IR'] = 'ur'
d['TION'] = 'shun'
d[' I'] = ' i'  #
d['I'] = 'ee'
d[' EN'] = ' en'  #
d['EN '] = 'ee'
d['F'] = 'ff'
d['E '] = 'e-a '
d[' U'] = ' u'  #
d['U'] = 'oo'
d['V'] = 'f'
d['W'] = 'v'

for i in range(0, 5):
    s = input()
    s = ' ' + s[:len(s)-1] + ' '
    for j, k in d.items():
        s = s.replace(j, k)
    s = s[1:len(s)-1].upper() + '.'
    print(s, 'BORK BORK BORK!')
