import sys
sys.path.insert(1, sys.argv[2])

from SentenceNetCreator import SentenceNetCreator
from SentenceNetVisitor import SentenceNetVisitor

from os import listdir
from os.path import isfile, join

pathdom1=sys.argv[1] + '\\Dominio1\\'
pathdom2=sys.argv[1] + '\\Dominio2\\'
fp1 = [ (pathdom1 + f) for f in listdir(pathdom1) if isfile(join(pathdom1,f)) ]
fp2 = [ (pathdom2 + f) for f in listdir(pathdom2) if isfile(join(pathdom2,f)) ]

print fp1 + fp2

EDGE_START_WEIGHT = 1.0
OCCURRENCES_POS = 0 # the tuple representing the number of occurrences is the first attribute (position 0) for each edge
OCCURRENCES_VALUE_POS = 1 # the value of the number of occurrences is in position 1 in the tuple ('occurrences', <occurrences_number>)
START_OCCURRENCES_NUM = 1 # the starting value for the number of occurrences, which will be placed in the node label

s1 = SentenceNetCreator()
s1.createNet(fp1)
n1 = s1.get_net()
v1 = SentenceNetVisitor(n1, EDGE_START_WEIGHT, START_OCCURRENCES_NUM)

s2 = SentenceNetCreator()
s2.createNet(fp2)
n2 = s1.get_net()
v2 = SentenceNetVisitor(n1, EDGE_START_WEIGHT, START_OCCURRENCES_NUM)


s1.write_graph("C:\Users\Lipari\Documents\Prova\prova1.gv")
s2.write_graph("C:\Users\Lipari\Documents\Prova\prova2.gv")
