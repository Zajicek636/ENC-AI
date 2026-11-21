import random

class CustomStrategy:
    @staticmethod
    def author_name():
        return "Radek Dvořák"

    @staticmethod
    def strategy_name():
        return "Radek Strategy WSLS"

    def __init__(self):
        self.reset()

    def reset(self):
        self.history_other = []
        self.history_my = []

    def last_move(self, my_move, other_move):
        self.history_my.append(my_move)
        self.history_other.append(other_move)

    def play(self):
        # WSLS: po zisku 3 nebo 5 bodů tah opakuji, po 0 nebo 1 jej změním.
        # Musí vracet výhradně 'C' nebo 'D'

        if not self.history_other:
            return 'C'  # První krok

        my_last = self.history_my[-1]
        other_last = self.history_other[-1]
        if other_last == 'C':
            return my_last
        return 'D' if my_last == 'C' else 'C'

def create_strategy():
    return CustomStrategy()
