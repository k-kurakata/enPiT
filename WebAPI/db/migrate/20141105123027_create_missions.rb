class CreateMissions < ActiveRecord::Migration
  def change
    create_table :missions do |t|
      t.text :body
      t.decimal :difficulty
      t.boolean :closed

      t.timestamps
    end
  end
end
