'''
@author: Giuseppe Lipari
'''
import nltk
import sys
from ProgressBar import progressBar
from pygraph.classes.digraph import digraph
sys.path.insert(1, sys.argv[2])
sys.path.insert(1, sys.argv[2]+'irutils')
from irutils.TextFilter import TextFilter
from SentenceNetCreator import SentenceNetCreator
from SentenceNetVisitor import SentenceNetVisitor
from os import listdir
from os.path import isfile, join

pathdom1=sys.argv[1] + '/Domain1/'
pathdom2=sys.argv[1] + '/Domain2/'
pathreq=sys.argv[1] + '/Requirements/'
pathres=sys.argv[1] + '/Result/'

fp1 = [ (pathdom1 + f) for f in listdir(pathdom1) if isfile(join(pathdom1,f)) ]
fp2 = [ (pathdom2 + f) for f in listdir(pathdom2) if isfile(join(pathdom2,f)) ]
bar=progressBar()
progress=0.0;
bar.setPercent(int(progress))
terms_filter = TextFilter()
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
n2 = s2.get_net()
v2 = SentenceNetVisitor(n2, EDGE_START_WEIGHT, START_OCCURRENCES_NUM)

#Apro il file dei requisiti ed associo un requisito ad ogni riga
path_file_req=pathreq + listdir(pathreq)[0]
#print path_file_req
req_file=open(path_file_req,"r")
reqs= req_file.readlines()
req_file.close()
#print reqs
nreq=len(reqs)*2+2
ind=1
overlap=0
overlap_file = open(pathres+"domain_overlap.txt","w")
domain1 = [(f) for f in listdir(pathdom1) if isfile(join(pathdom1,f))]
domain2 = [(f) for f in listdir(pathdom2) if isfile(join(pathdom2,f))]
domains='Domain1:' + ", ".join(domain1) + '\nDomain2:' + ", ".join(domain2) + ' \n'
overlap_file.write(domains)
#Ciclo creazione e salvataggio sotto grafi cammini + jaccard
for req in reqs:
        progress+=1
        #print 'Req:' + req
	filtered_sent = terms_filter.filter_all(req)
	#print 'Filter: ' + filtered_sent
	path1, path_weight1 = v1.search_A_star(filtered_sent)
	path2, path_weight2 = v2.search_A_star(filtered_sent)
	path1_tokens = nltk.word_tokenize(path1)
	path2_tokens = nltk.word_tokenize(path2)
	current_subgraph = digraph()
	current_subgraph2 = digraph()

        for index, term in enumerate(path1_tokens):
   		subgraph_req = s1.get_connected_subgraph(term)
   		current_subgraph = s1.get_merged_subgraph(current_subgraph, subgraph_req)
	SentenceNetCreator.write_subgraph(pathres + 'R%d-'%(ind)+ req[0:10] + '-dom1.gv', current_subgraph)
        x=float(progress/nreq)
        bar.setPercent(int(x*100))
        for index, term in enumerate(path2_tokens):
   		subgraph_req2 = s2.get_connected_subgraph(term)
   		current_subgraph2 = s2.get_merged_subgraph(current_subgraph2, subgraph_req2)
	SentenceNetCreator.write_subgraph(pathres + 'R%d-'%(ind)+ req[0:10] + '-dom2.gv', current_subgraph2)
        path_subgraph1=' '.join(current_subgraph.nodes())
	path_subgraph2=' '.join(current_subgraph2.nodes())
	overlap= SentenceNetCreator.evaluate_jaccard(s1,path_subgraph1,path_subgraph2,filtered_sent)
        r='R%d Domain Overlap\n%.10f\n'%(ind,jac)
        overlap_file.write(r)
        ind+=1
        progress+=1
        x=float(progress/nreq)
        bar.setPercent(int(x*100))
overlap_file.close()
s1.write_graph(pathres + 'Graph-dom1.gv')
progress+=1
x=float(progress/nreq)
bar.setPercent(int(x*100))
s2.write_graph(pathres + 'Graph-dom2.gv')
progress+=1
x=float(progress/nreq)
bar.setPercent(int(x*100))
