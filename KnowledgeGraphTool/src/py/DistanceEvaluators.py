'''
@author: Giuseppe Lipari
'''
from __future__ import division

import nltk
from irutils.TextFilter import TextFilter
from pygraph.classes.digraph import digraph
from SentenceNetCreator import SentenceNetCreator
from SentenceNetVisitor import SentenceNetVisitor

class DistanceEvaluators(object):
    '''
    this class provide methods for evaluate distance of two graph path
    '''

    '''
    Method for Jaccard Evaluation
    '''
    def jaccard_evaluator(self,requirement,sentence_net1,sentence_net2,sentence_visitor1,sentence_visitor2):
        
        terms_filter = TextFilter()
        filtered_sent = terms_filter.filter_all(requirement)
        
        path1, path_weight1 = sentence_visitor1.search_A_star(filtered_sent)
        path2, path_weight2 = sentence_visitor2.search_A_star(filtered_sent)
        path1_tokens = nltk.word_tokenize(path1)
	path2_tokens = nltk.word_tokenize(path2)
        current_subgraph1 = digraph()
        current_subgraph2 = digraph()
        
        for index, term in enumerate(path1_tokens):
            subgraph1_req = sentence_net1.get_connected_subgraph(term)
            current_subgraph1 = sentence_net1.get_merged_subgraph(current_subgraph1,subgraph1_req)
            del subgraph1_req
        for index, term in enumerate(path2_tokens):
            subgraph2_req = sentence_net2.get_connected_subgraph(term)
            current_subgraph2 = sentence_net2.get_merged_subgraph(current_subgraph2,subgraph2_req)
            del subgraph2_req
        """
        evaluate the jaccard distance but deletes the words that are contained in filtered_sent
        """
        setpath1 = set(current_subgraph1.nodes())
        setdiff=set(nltk.word_tokenize(filtered_sent))
        setpath2 = set(current_subgraph2.nodes())
        intersection_len = len((setpath1 & setpath2).difference(setdiff))
        union_len = len((setpath1 | setpath2).difference(setdiff))            

        if union_len != 0:
            jaccard = intersection_len / union_len
        else:
            jaccard = 1.0

        return jaccard, current_subgraph1, current_subgraph2    
            
