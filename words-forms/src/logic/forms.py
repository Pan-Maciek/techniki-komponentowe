import morfeusz2
from typing import List


class Forms:
    words: List[str]
    errors: List[str]

    def __init__(self, words: List[str]):
        self.words = words

    def find_form(self):
        self.errors = []
        forms = []
        try:
            morf = morfeusz2.Morfeusz()
            for word in self.words:
                local = []
                generated = morf.generate(word)
                for gen in generated:
                    form = gen[0]
                    if form not in self.words:
                        local.append(form)
                local = local[0:6]
                for loc in local:
                    forms.append(loc)
            forms = list(dict.fromkeys(forms))
        except Exception as e:
            self.errors.append(str(e))

        return forms
