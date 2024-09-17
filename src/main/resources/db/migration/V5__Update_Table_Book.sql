-- Adicionar campo image_url, se ainda não existir
ALTER TABLE `livro` ADD COLUMN `image_url` VARCHAR(255);

-- Atualizar campo image_url com links de imagens para os livros existentes
UPDATE `livro`
SET `image_url` = CASE `titulo`
    WHEN '1984' THEN 'https://example.com/images/books/1984.jpg'
    WHEN 'Harry Potter and the Philosopher\'s Stone' THEN 'https://example.com/images/books/harry-potter.jpg'
    WHEN 'The Lord of the Rings' THEN 'https://example.com/images/books/lord-of-the-rings.jpg'
    WHEN 'To Kill a Mockingbird' THEN 'https://example.com/images/books/to-kill-a-mockingbird.jpg'
    WHEN 'The Great Gatsby' THEN 'https://example.com/images/books/great-gatsby.jpg'
    WHEN 'One Hundred Years of Solitude' THEN 'https://example.com/images/books/one-hundred-years-of-solitude.jpg'
    WHEN 'War and Peace' THEN 'https://example.com/images/books/war-and-peace.jpg'
    WHEN 'Moby-Dick' THEN 'https://example.com/images/books/moby-dick.jpg'
    WHEN 'Pride and Prejudice' THEN 'https://example.com/images/books/pride-and-prejudice.jpg'
    WHEN 'The Adventures of Huckleberry Finn' THEN 'https://example.com/images/books/huckleberry-finn.jpg'
    WHEN 'A Tale of Two Cities' THEN 'https://example.com/images/books/tale-of-two-cities.jpg'
    WHEN 'Brave New World' THEN 'https://example.com/images/books/brave-new-world.jpg'
    WHEN 'The Old Man and the Sea' THEN 'https://example.com/images/books/old-man-and-sea.jpg'
    WHEN 'Fahrenheit 451' THEN 'https://example.com/images/books/fahrenheit-451.jpg'
    WHEN 'Catch-22' THEN 'https://example.com/images/books/catch-22.jpg'
    WHEN 'Slaughterhouse-Five' THEN 'https://example.com/images/books/slaughterhouse-five.jpg'
    WHEN 'Crime and Punishment' THEN 'https://example.com/images/books/crime-and-punishment.jpg'
    WHEN 'Frankenstein' THEN 'https://example.com/images/books/frankenstein.jpg'
    WHEN 'Mrs. Dalloway' THEN 'https://example.com/images/books/mrs-dalloway.jpg'
    WHEN 'Ulysses' THEN 'https://example.com/images/books/ulysses.jpg'
    ELSE 'https://example.com/images/books/default.jpg'
END;
