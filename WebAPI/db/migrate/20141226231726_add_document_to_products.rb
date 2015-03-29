class AddDocumentToProducts < ActiveRecord::Migration
  def change
    add_column :products, :document, :boolean
  end
end
