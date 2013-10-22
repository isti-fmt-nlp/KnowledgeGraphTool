from SentenceNetCreator import SentenceNetCreator
from SentenceNetVisitor import SentenceNetVisitor

fp1= "C:\\Users\\Lipari\\Documents\\Prova\\Progetto1\\Requisiti\\warc-tools_phase_III_frs_v8.pdf"
fp2= "C:\\Users\\Lipari\\Documents\\Prova\\Progetto1\\Dominio1\\prova.txt"

EDGE_START_WEIGHT = 1.0
OCCURRENCES_POS = 0 # the tuple representing the number of occurrences is the first attribute (position 0) for each edge
OCCURRENCES_VALUE_POS = 1 # the value of the number of occurrences is in position 1 in the tuple ('occurrences', <occurrences_number>)
START_OCCURRENCES_NUM = 1 # the starting value for the number of occurrences, which will be placed in the node label

s1 = SentenceNetCreator()
s1.createNet([fp1])
n1 = s1.get_net()
v1 = SentenceNetVisitor(n1, EDGE_START_WEIGHT, START_OCCURRENCES_NUM)

s2 = SentenceNetCreator()
s2.createNet([fp2])
n2 = s1.get_net()
v2 = SentenceNetVisitor(n1, EDGE_START_WEIGHT, START_OCCURRENCES_NUM)


s1.write_graph("C:\Users\Lipari\Documents\Prova\prova1.gv")
s2.write_graph("C:\Users\Lipari\Documents\Prova\prova2.gv")
