class PurchasedProduct < ActiveRecord::Base
  belongs_to :user
  belongs_to :product

  def self.purchased?(product)
    if all.select {|x| x.product_id == product.id}.empty? then
      false
    else
      true
    end
  end

end
