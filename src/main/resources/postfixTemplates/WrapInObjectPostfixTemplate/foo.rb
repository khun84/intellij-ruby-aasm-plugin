class Foo
  has_many :sons
  event :book

  aasm :state do

  end

  has_plenty :hihi do

  end

  def foo
    sons
  end

  def bar
  end
end