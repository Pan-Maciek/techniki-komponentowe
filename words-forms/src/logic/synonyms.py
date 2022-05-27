from typing import List
import os


class Synonyms:
    words: List[str]
    errors: List[str]

    def __init__(self, words: List[str]):
        self.words = words

    def find_form(self):
        self.errors = []
        global_synonyms = []
        path = os.path.join(os.getcwd(), 'thesaurus_full.txt')
        try:
            for word in self.words:
                local_synonyms = []
                word = word.lower()
                with open(path, 'r', encoding='utf-8') as f:
                    for line in f:
                        dict_word = line.split(';')
                        if dict_word[0] == word:
                            local_synonyms.append(dict_word)
                synonyms = [item for sublist in local_synonyms for item in sublist]
                local_synonyms = Synonyms.words_filter(synonyms)
                local_synonyms = local_synonyms[0:6]
                for local in local_synonyms:
                    if local not in self.words:
                        global_synonyms.append(local)
        except Exception as e:
            self.errors.append(str(e))

        return global_synonyms

    @staticmethod
    def words_filter(syn):
        syn = [word.strip() for word in syn]
        syn = [syn.remove(word) if ' ' in word else word for word in syn]
        syn = [word for word in syn if word is not None]
        return syn