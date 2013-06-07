
class ReversePolishNotation

  def initialize val
    @val = val
  end

  def method_missing method_id, *args
    ReversePolishNotation.new @val + args[0].to_s + method_id.to_s
  end

  def to_s
    @val
  end

  def to_ary
    [to_s]
  end

end

a = ReversePolishNotation.new "a"
b = ReversePolishNotation.new "b"
c = ReversePolishNotation.new "c"
d = ReversePolishNotation.new "d"
e = ReversePolishNotation.new "e"
f = ReversePolishNotation.new "f"
g = ReversePolishNotation.new "g"
h = ReversePolishNotation.new "h"
i = ReversePolishNotation.new "i"
j = ReversePolishNotation.new "j"
k = ReversePolishNotation.new "k"
l = ReversePolishNotation.new "l"
m = ReversePolishNotation.new "m"
n = ReversePolishNotation.new "n"
o = ReversePolishNotation.new "o"
p = ReversePolishNotation.new "p"
q = ReversePolishNotation.new "q"
r = ReversePolishNotation.new "r"
s = ReversePolishNotation.new "s"
t = ReversePolishNotation.new "t"
u = ReversePolishNotation.new "u"
v = ReversePolishNotation.new "v"
w = ReversePolishNotation.new "w"
x = ReversePolishNotation.new "x"
y = ReversePolishNotation.new "y"
z = ReversePolishNotation.new "z"

Range.new(1, Integer(STDIN.gets)).each do
  puts (eval STDIN.gets).to_s
end