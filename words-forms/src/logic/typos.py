from typing import List


class Typos:
    words: List[str]
    errors: List[str]

    def __init__(self, words: List[str]):
        self.words = words

    def find_form(self):
        self.errors = []
        global_typos = []
        try:
            for word in self.words:
                index = 0
                word.lower()
                local_typos = []
                while len(local_typos) < len(word):
                    typo = Typos.generate_typos(word, index)
                    if typo in local_typos or typo == word:
                        pass
                    else:
                        local_typos.append(typo)
                        index += 1
                for local in local_typos:
                    global_typos.append(local)
        except Exception as e:
            self.errors.append(str(e))

        return global_typos

    @staticmethod
    def generate_typos(word: str, index: int):
        keyApprox = {}
        keyApprox['q'] = "wasedzx"
        keyApprox['w'] = "qesadrfcx"
        keyApprox['e'] = "wrsfdqazxcvgt"
        keyApprox['r'] = "etdgfwsxcvgt"
        keyApprox['t'] = "ryfhgedcvbnju"
        keyApprox['y'] = "tugjhrfvbnji"
        keyApprox['u'] = "yihkjtgbnmlo"
        keyApprox['i'] = "uojlkyhnmlp"
        keyApprox['o'] = "ipklujm"
        keyApprox['p'] = "lo['ik"

        keyApprox['a'] = "qszwxwdce"
        keyApprox['s'] = "śwxadrfv"
        keyApprox['d'] = "ecsfaqgbv"
        keyApprox['f'] = "dgrvwsxyhn"
        keyApprox['g'] = "tbfhedcyjn"
        keyApprox['h'] = "yngjfrvkim"
        keyApprox['j'] = "hknugtblom"
        keyApprox['k'] = "jlinyhn"
        keyApprox['l'] = "okmpujn"

        keyApprox['z'] = "axsvde"
        keyApprox['x'] = "zcsdbvfrewq"
        keyApprox['c'] = "xvdfzswergb"
        keyApprox['v'] = "cfbgxdertyn"
        keyApprox['b'] = "vnghcftyun"
        keyApprox['n'] = "bmhjvgtuik"
        keyApprox['m'] = "nkjloik"
        keyApprox[' '] = " "

        letter_list = []
        for letter in word:
            letter_list.append(letter)
        letter_to_change = letter_list[index]
        if letter_to_change not in keyApprox.keys():
            new_letter = letter_to_change
        else:
            new_letter = keyApprox[letter_to_change][0]
        letter_list[index] = new_letter
        generated_typo = ""
        for letter in letter_list:
            generated_typo += str(letter)
        return generated_typo
