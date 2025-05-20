package hoanvt.librarymanagementmain.service.impl;

import hoanvt.librarymanagementmain.dto.BorrowRecordRequestDTO;
import hoanvt.librarymanagementmain.dto.BorrowRecordResponseDTO;
import hoanvt.librarymanagementmain.entity.Book;
import hoanvt.librarymanagementmain.entity.BorrowRecord;
import hoanvt.librarymanagementmain.entity.User;
import hoanvt.librarymanagementmain.repository.BookRepository;
import hoanvt.librarymanagementmain.repository.BorrowRecordRepository;
import hoanvt.librarymanagementmain.repository.UserRepository;
import hoanvt.librarymanagementmain.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    private BorrowRecordResponseDTO toResponseDTO(BorrowRecord br) {
        BorrowRecordResponseDTO dto = new BorrowRecordResponseDTO();
        dto.setId(br.getId());
        dto.setUserId(br.getUser().getId());
        dto.setUsername(br.getUser().getUsername());
        dto.setBookId(br.getBook().getId());
        dto.setBookTitle(br.getBook().getTitle());
        dto.setBorrowDate(br.getBorrowDate());
        dto.setReturnDate(br.getReturnDate());
        dto.setQuantity(br.getQuantity());
        return dto;
    }

    @Override
    public List<BorrowRecordResponseDTO> getMyBorrowRecords(Long userId) {
        List<BorrowRecord> all = borrowRecordRepository.findByUserIdOrderByReturnDateAscBorrowDateDesc(userId);
        // Unreturned first, then by borrow date desc
        all.sort(Comparator.comparing((BorrowRecord b) -> b.getReturnDate() == null)
                .reversed()
                .thenComparing(BorrowRecord::getBorrowDate, Comparator.reverseOrder()));
        return all.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public BorrowRecordResponseDTO createBorrowRecord(BorrowRecordRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        BorrowRecord br = new BorrowRecord();
        br.setUser(user);
        br.setBook(book);
        br.setBorrowDate(dto.getBorrowDate());
        br.setReturnDate(dto.getReturnDate());
        br.setQuantity(dto.getQuantity());
        br = borrowRecordRepository.save(br);
        return toResponseDTO(br);
    }

    @Override
    public BorrowRecordResponseDTO getBorrowRecordById(Long id, Long userId) {
        return borrowRecordRepository.findById(id)
                .filter(br -> br.getUser().getId().equals(userId))
                .map(this::toResponseDTO)
                .orElse(null);
    }

    @Override
    public BorrowRecordResponseDTO updateBorrowRecord(Long id, BorrowRecordRequestDTO dto, Long userId) {
        Optional<BorrowRecord> opt = borrowRecordRepository.findById(id)
                .filter(br -> br.getUser().getId().equals(userId));
        if (opt.isPresent()) {
            BorrowRecord br = opt.get();
            if (dto.getBookId() != null && !br.getBook().getId().equals(dto.getBookId())) {
                Book book = bookRepository.findById(dto.getBookId())
                        .orElseThrow(() -> new RuntimeException("Book not found"));
                br.setBook(book);
            }
            if (dto.getBorrowDate() != null) br.setBorrowDate(dto.getBorrowDate());
            if (dto.getReturnDate() != null) br.setReturnDate(dto.getReturnDate());
            if (dto.getQuantity() != null) br.setQuantity(dto.getQuantity());
            br = borrowRecordRepository.save(br);
            return toResponseDTO(br);
        }
        return null;
    }

    @Override
    public void deleteBorrowRecord(Long id, Long userId) {
        borrowRecordRepository.findById(id)
                .filter(br -> br.getUser().getId().equals(userId))
                .ifPresent(borrowRecordRepository::delete);
    }
}
