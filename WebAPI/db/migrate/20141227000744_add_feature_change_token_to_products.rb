class AddFeatureChangeTokenToProducts < ActiveRecord::Migration
  def change
     add_column :products, :feature_change_token, :string, null: true 
  end
end
