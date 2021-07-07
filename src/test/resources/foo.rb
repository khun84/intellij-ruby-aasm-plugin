class Test
  def self.event(ename)
    ename
  end

  event :foo
  event :bar
  field :name
end

t = Test.new
