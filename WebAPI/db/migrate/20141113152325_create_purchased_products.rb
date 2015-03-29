class CreatePurchasedProducts < ActiveRecord::Migration
  def change
    create_table :purchased_products do |t|
      t.integer :user_id
      t.integer :product_id

      t.timestamps
    end
  end
end