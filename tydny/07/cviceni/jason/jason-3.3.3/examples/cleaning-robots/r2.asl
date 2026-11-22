// Both incinerators run this program.
+garbage(Me) : .my_name(Me) <- !empty(Me).

+!empty(Me) : garbage(Me) <- burn(garb); !empty(Me).
+!empty(_).
