package com.devsuperior.dslist.services;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDto;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public List<GameMinDto> findAll() {
        List<Game> games = gameRepository.findAll();
        if (games.isEmpty()) return null;
        return games.stream().map(GameMinDto::new).toList();
    }

    @Transactional(readOnly = true)
    public Optional<GameDTO> findById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isPresent()) return game.map(GameDTO::new);
        return Optional.empty();
    }
}